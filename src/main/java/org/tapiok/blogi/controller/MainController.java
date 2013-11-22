package org.tapiok.blogi.controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tapiok.blogi.model.Comment;
import org.tapiok.blogi.repo.UserRepository;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;

/**
 *
 * @author Tapio
 */
@Controller
@RequestMapping("/")
public class MainController {

    // Dao luokat
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    // Hakee 5 uusinta postia aina sivulle
    @ModelAttribute("posts")
    public List<Post> getPosts() {
        PageRequest pageRequest = new PageRequest(0, 20, Sort.Direction.DESC, "created");
        return postRepository.findAll(pageRequest).getContent();
    }

    // etusivu
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        Logger.getLogger("DebugInfoLogger").log(Level.INFO, "@ MainController.index()");

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "home");
        return mav;
    }

    // Uuden postin postailukaavake
    @RequestMapping(value = "/postform", method = RequestMethod.GET)
    public ModelAndView post(ModelMap mm) {
        Logger.getLogger("DebugInfoLogger").log(Level.INFO, "@ MainController.post()");

        mm.put("post", new Post());
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("method", "post");
        mav.addObject("page", "postform");
        return mav;
    }

    // Postin muokkailukaavake
    @RequestMapping(value = "/postform/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable Long id, ModelMap mm) {
        Post postToEdit = postRepository.findById(id);

        postToEdit.setBody(postToEdit.getBody().replace("<br>", ""));
        mm.put("post", postToEdit);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("method", "put");
        mav.addObject("page", "postform");

        return mav;
    }

    // Postin näyttäminen
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView showPost(@PathVariable Long id, ModelMap mm) {
        
        mm.put("comment", new Comment());
        Post thePost = postRepository.findById(id);
        thePost.setComments(commentRepository.findByPostId(id));
        Logger.getLogger(MainController.class.getName())
                .log(Level.SEVERE, "Comments: " + thePost.getComments().toString());
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "showPost");
        mav.addObject("postToShow", thePost);

        return mav;
    }

    // Apuluokka postin tuhoiluun, koska html linkissä ei voi laittaa methodiksi DELETE..
    @RequestMapping(value = "/postDelete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePostMediator(@PathVariable Long id) {
        deletePost(id);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Post deleted!");

        return mav;
    }

    // Postin tuhoilu
    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public ModelAndView deletePost(@PathVariable Long id) {
        postRepository.delete(id);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Post deleted!");

        return mav;
    }

    // Postin muokkaus
    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    public ModelAndView editPost(@PathVariable Long id, @ModelAttribute("post") @Valid Post postValues, BindingResult br) {

        if (br.hasErrors()) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("page", "postform");
            mav.addObject("method", "put");
            return mav;
        }
        
        Post thePost = postRepository.findById(id);
        thePost.setBody(postValues.getBody().replace("\n", "<br>\n"));
        thePost.setTitle(postValues.getTitle());
        postRepository.saveAndFlush(thePost);

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Post edited!");

        return mav;
    }

    // Kommentin postaus
    @RequestMapping(value = "/comment/{postId}", method = RequestMethod.POST)
    public ModelAndView saveComment(@PathVariable("postId") Long id, @ModelAttribute("comment") @Valid Comment commentValues, BindingResult br) {
        Logger.getLogger(MainController.class.getName())
                .log(Level.SEVERE, "@ MainController.saveComment() values: " + commentValues.toString() + "\npost: ");

        if (br.hasErrors()) {
            for (ObjectError oe : br.getAllErrors()) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, oe.toString());
            }
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("page", "message");
            mav.addObject("message", "Error saving comment..");
            return mav;
        }

        ModelAndView mav = new ModelAndView("index");

        try {
            PolicyFactory policy = new HtmlPolicyBuilder().toFactory();
            Post thePost = postRepository.findById(id);
            commentValues.setPost(thePost);
            commentValues.setAuthor(policy.sanitize(commentValues.getAuthor()));
            commentValues.setBody(policy.sanitize(commentValues.getBody().replace("\n", "<br>\n")));
            commentRepository.saveAndFlush(commentValues);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mav.addObject("page", "message");
        mav.addObject("message", "Comment saved!");
        return mav;
    }
    
    // Kommentin deletöilyn apumetodi, koska linkkiin ei voi laittaa metodia
    @RequestMapping(value = "commentDelete/{commentId}", method = RequestMethod.GET) 
    public ModelAndView deleteCommentMediator(@PathVariable("commentId") Long commentId) {
        deleteComment(commentId);
        
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Comment deleted!");
        return mav;
    }
    
    // Kommentin deletöily
    @RequestMapping(value = "comment/{commentId}", method = RequestMethod.DELETE) 
    public ModelAndView deleteComment(@PathVariable("commentId") Long commentId) {
        commentRepository.delete(commentId);
        
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page", "message");
        mav.addObject("message", "Comment deleted!");
        return mav;
    }

    // Uuden postin postailu
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ModelAndView savePost(@ModelAttribute("post") @Valid Post postValues, BindingResult br, Principal principal) {
        Logger.getLogger("DebugInfoLogger").log(Level.INFO, "@ MainController.savePost() values: " + postValues.toString() + "\nuser: " + principal.getName());

        if (br.hasErrors()) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("page", "postform");
            mav.addObject("method", "post");
            return mav;
        }

        ModelAndView mav = new ModelAndView("index");

        try {
            postValues.setAuthor(userRepository.findByUsername(principal.getName()));
            Logger.getLogger("DebugInfoLogger").log(Level.INFO, "found userid: " + userRepository.findByUsername(principal.getName()));
            postValues.setBody(postValues.getBody().replace("\n", "<br>\n"));
            postRepository.saveAndFlush(postValues);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mav.addObject("page", "message");
        mav.addObject("message", "Post saved!");
        return mav;
    }
}
