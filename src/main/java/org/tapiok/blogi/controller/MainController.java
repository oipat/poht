package org.tapiok.blogi.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tapiok.blogi.model.Comment;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.service.PostService;

@Controller
public class MainController {

	@Autowired
	PostService postService;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory
			.getLogger(MainController.class);

	@ModelAttribute("posts")
	public List<Post> getPosts() {
		return postService.getAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("page", "home");
		return mav;
	}

	@RequestMapping(value = "/postform", method = RequestMethod.GET)
	public ModelAndView post() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("method", "post");
		mav.addObject("page", "postform");
		mav.addObject("post", new Post());
		return mav;
	}

	@RequestMapping(value = "/postform/{id}", method = RequestMethod.GET)
	public ModelAndView editPost(@PathVariable Long id) {
		Post postToEdit = postService.findById(id);
		postToEdit.setBody(postToEdit.getBody().replace("<br>", ""));
		
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("post", postToEdit);
		mav.addObject("method", "put");
		mav.addObject("page", "postform");

		return mav;
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	public ModelAndView showPost(@PathVariable Long id) {

		Post post = postService.findById(id);
		post.setComments(commentRepository.findByPostId(id));
		logger.debug("Showing post: {}", post);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("page", "showPost");
		mav.addObject("postToShow", post);
		mav.addObject("comment", new Comment());

		return mav;
	}

	@RequestMapping(value = "/postDelete/{id}", method = RequestMethod.GET)
	public ModelAndView deletePostMediator(@PathVariable Long id) {
		return deletePost(id);
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	public ModelAndView deletePost(@PathVariable Long id) {
		postService.deleteById(id);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("page", "message");
		mav.addObject("message", "Post deleted!");

		return mav;
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
	public ModelAndView editPost(@PathVariable Long id,
			@Valid Post post, BindingResult br) {
		logger.debug("MainController.savePost() values: {}\nuser: {}",
				post.toString());

		if (br.hasErrors()) {
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("page", "postform");
			mav.addObject("method", "put");
			return mav;
		}

		Post savedPost = postService.findById(id);
		savedPost.setBody(savedPost.getBody().replace("\n", "<br>\n"));
		savedPost.setTitle(savedPost.getTitle());
		postService.savePost(savedPost);

		ModelAndView mav = new ModelAndView("index");
		mav.addObject("page", "message");
		mav.addObject("message", "Post edited!");

		return mav;
	}

	@RequestMapping(value = "/comment/{postId}", method = RequestMethod.POST)
	public ModelAndView saveComment(@PathVariable("postId") Long id,
			@ModelAttribute("comment") @Valid Comment commentValues,
			BindingResult br) {
		if (br.hasErrors()) {
			for (ObjectError oe : br.getAllErrors()) {
				logger.debug(oe.toString());
			}
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("page", "message");
			mav.addObject("message", "Error saving comment..");
			return mav;
		}

		ModelAndView mav = new ModelAndView("index");

		PolicyFactory policy = new HtmlPolicyBuilder().toFactory();
		Post savedPost = postService.findById(id);
		commentValues.setPost(savedPost);
		commentValues.setAuthor(policy.sanitize(commentValues.getAuthor()));
		commentValues.setBody(policy.sanitize(commentValues.getBody().replace(
				"\n", "<br>\n")));
		commentRepository.saveAndFlush(commentValues);

		mav.addObject("page", "message");
		mav.addObject("message", "Comment saved!");
		return mav;
	}

	@RequestMapping(value = "commentDelete/{commentId}", method = RequestMethod.GET)
	public ModelAndView deleteCommentMediator(
			@PathVariable("commentId") Long commentId) {
		return deleteComment(commentId);
	}

	@RequestMapping(value = "comment/{commentId}", method = RequestMethod.DELETE)
	public ModelAndView deleteComment(@PathVariable("commentId") Long commentId) {
		commentRepository.delete(commentId);

		ModelAndView mav = new ModelAndView("index");
		mav.addObject("page", "message");
		mav.addObject("message", "Comment deleted!");
		return mav;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ModelAndView savePost(
			@ModelAttribute("post") @Valid Post post, BindingResult br,
			Principal principal) {
		logger.debug("MainController.savePost() values: {}\nuser: {}",
				post.toString(), principal.getName());

		if (br.hasErrors()) {
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("page", "postform");
			mav.addObject("method", "post");
			return mav;
		}

		ModelAndView mav = new ModelAndView("index");

		post.setAuthor(userRepository.findByUsername(principal.getName()));
		logger.info("Found user: {}",
				userRepository.findByUsername(principal.getName())
						.getUsername());
		post.setBody(post.getBody().replace("\n", "<br>\n"));
		postService.savePost(post);

		mav.addObject("page", "message");
		mav.addObject("message", "Post saved!");
		return mav;
	}
}
