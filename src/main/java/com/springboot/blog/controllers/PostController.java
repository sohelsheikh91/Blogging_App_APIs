package com.springboot.blog.controllers;

import com.springboot.blog.config.AppConstants;
import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.PostDTO;
import com.springboot.blog.payloads.PostResponse;
import com.springboot.blog.services.FileService;
import com.springboot.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileservice;

    @Value("${project.image}")
    private String path;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId )
    {
        PostDTO createPost = this.postService.createPost(postDTO, categoryId, userId);

        return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
    }
    //Get ALL Posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer number,
                                                    @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size,
                                                    @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){

        PostResponse postResponse = this.postService.getAllPost(number, size, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    //get posts by ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostsByID(@PathVariable Integer postId){

        PostDTO postDTO = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    //Get Post by Users
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                        @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer number,
                                                        @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size,
                                                        @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                        @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){


        PostResponse postResponse = this.postService.getPostsByUser(userId, number, size, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    //Get Posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer number,
                                                            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size,
                                                            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){

        PostResponse postResponse = this.postService.getPostsByCategory(categoryId, number, size, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
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
    //search Posts
    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPosts(@PathVariable("keyword") String keyword){
        List<PostDTO> postDTOList = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    // Post Image Upload
    @PostMapping("posts/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadImage(
            @RequestParam("image") MultipartFile file,
            @PathVariable("postId") Integer postId) throws IOException {

        PostDTO postDTO = this.postService.getPostById(postId);  //it should above as if not found it will throw Exception
        String fileName = this.fileservice.uploadImage(path, file);

        postDTO.setImageName(fileName);
        return new ResponseEntity<>(this.postService.updatePost(postDTO,postId),HttpStatus.OK);
    }
    //get Image
    @GetMapping("posts/image/{imageName}")
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {

        InputStream is = this.fileservice.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is,response.getOutputStream());

    }

}

