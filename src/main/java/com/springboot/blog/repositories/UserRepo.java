package com.springboot.blog.repositories;

import com.springboot.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> { // repo will be interface, <EntityName, IDtype>

}
