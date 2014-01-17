package org.tapiok.blogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.repo.CommentRepository;
import org.tapiok.blogi.repo.PostRepository;
import org.tapiok.blogi.service.PostService;


@Service
@Transactional(readOnly = false)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

	@Override
    @Transactional(readOnly = true)
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@Override
    @Transactional(readOnly = true)
	public Post findById(long id) {
		Post post = postRepository.findById(id);
		return post;
	}

	@Override
	public void deleteById(Long id) {
		postRepository.delete(id);
	}

	@Override
	public Post savePost(Post post) {
		return postRepository.saveAndFlush(post);
	}
}
