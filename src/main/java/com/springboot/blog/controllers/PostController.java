package com.springboot.blog.controllers;

import com.springboot.blog.entities.Post;
import com.springboot.blog.payloads.PostDTO;
import com.springboot.blog.services.PostService;
import com.springboot.blog.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId )
    {
        PostDTO createPost = this.postService.createPost(postDTO, categoryId, userId);

        return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
    }

    //Get Post by Category
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){

        List<PostDTO> postDTOList = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
    //Get Posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){

        List<PostDTO> postDTOList = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTOList, HttpStatus.OK);
    }

}

