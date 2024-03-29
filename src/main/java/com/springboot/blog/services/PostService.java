package com.springboot.blog.services;

import com.springboot.blog.entities.Post;
import com.springboot.blog.payloads.PostDTO;
import com.springboot.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    //update

    PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll Post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get Single Post
    PostDTO getPostById(Integer postId);

    //getAll Post by Category
    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //getAll Post by User
    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //search Posts
    List<PostDTO> searchPosts(String keyword);

}
