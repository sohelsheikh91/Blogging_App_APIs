package com.springboot.blog.services;

import com.springboot.blog.payloads.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postID);
    void deleteComment(Integer commentId);
}
