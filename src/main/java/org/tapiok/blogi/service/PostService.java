package org.tapiok.blogi.service;

import java.util.List;

import org.tapiok.blogi.model.Post;

public interface PostService {
    public Post findById(long id);
	public void deleteById(Long id);
	public Post savePost(Post post);
	public List<Post> getAll();
}
