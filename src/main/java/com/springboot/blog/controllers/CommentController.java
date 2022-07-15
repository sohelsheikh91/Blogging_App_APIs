package com.springboot.blog.controllers;

import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.CommentDTO;
import com.springboot.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId){
        CommentDTO createdComment = this.commentService.createComment(commentDTO,postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{commentId}")
    public ApiResponse deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);

        return new ApiResponse("Comment Successfully Deleted", true);
    }
}
