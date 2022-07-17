package com.springboot.blog.repositories;

import com.springboot.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@NoRepositoryBean  , no annotation required like @service or @Component
//At runtime dynamically all repository interface will be scanned by Spring and will be stored as
// implementation class named Proxy class in proxy package
//this implementation class will be autowired , check UnitTest
//https://stackoverflow.com/a/51918303
public interface UserRepo extends JpaRepository<User,Integer> { // repo will be interface, <EntityName, IDtype>

    Optional<User> findByEmail(String email);
}
