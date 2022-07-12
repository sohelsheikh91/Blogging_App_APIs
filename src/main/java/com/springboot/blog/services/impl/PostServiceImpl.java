package com.springboot.blog.services.impl;

import com.springboot.blog.entities.Category;
import com.springboot.blog.entities.Post;
import com.springboot.blog.entities.User;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.payloads.PostDTO;
import com.springboot.blog.payloads.PostResponse;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.repositories.PostRepo;
import com.springboot.blog.repositories.UserRepo;
import com.springboot.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

        Post post = this.modelMapper.map(postDTO, Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> pageWithPosts = this.postRepo.findAll(pageable);
        List<Post> posts = pageWithPosts.getContent();

        List<PostDTO> postDTOList = posts.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pageWithPosts.getNumber());
        postResponse.setPageSize(pageWithPosts.getSize());
        postResponse.setTotalElements(pageWithPosts.getTotalElements());
        postResponse.setLastPage(pageWithPosts.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> postList = this.postRepo.findByCategory(cat);
        List<PostDTO> postDTOList = postList.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDTO> postDTOList = posts.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

        return postDTOList;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return null;
    }
}
