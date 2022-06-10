package com.springboot.blog.services;

import com.springboot.blog.entities.Post;
import com.springboot.blog.payloads.PostDTO;

import java.util.List;

public interface PostService {

    //create
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    //update

    PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll Post
    List<PostDTO> getAllPost();

    //get Single Post
    PostDTO getPostById(Integer postId);

    //getAll Post by Category
    List<PostDTO> getPostsByCategory(Integer categoryId);

    //getAll Post by User
    List<PostDTO> getPostsByUser(Integer userId);

    //search Posts
    List<PostDTO> searchPosts(String keyword);

}
