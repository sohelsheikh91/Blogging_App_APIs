package com.springboot.blog.repositories;

import com.springboot.blog.entities.Category;
import com.springboot.blog.entities.Post;
import com.springboot.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category catergory, Pageable pageable);
}
