package com.springboot.blog.controllers;

import com.springboot.blog.entities.Post;
import com.springboot.blog.payloads.ApiResponse;
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
    //Get ALL Posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer number,
                                                     @RequestParam(name = "pageSize", defaultValue = "1", required = false) Integer size){

        List<PostDTO> postDTOList = this.postService.getAllPost(number, size);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
    //get posts by ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostsByID(@PathVariable Integer postId){

        PostDTO postDTO = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    //Get Post by Users
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

    //Delete Post by Id
    @DeleteMapping("posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post Deleted Successfully", true);
    }
    //Update Post
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer postId){
        PostDTO updatedPostDTO = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
    }
}

