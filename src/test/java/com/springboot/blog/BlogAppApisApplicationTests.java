package com.springboot.blog;

import com.springboot.blog.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {
    @Autowired
    private UserRepo userRepo;
    @Test
    void contextLoads() {
    }
    //User Repo
    @Test
    public void repoTest(){
        System.out.println(userRepo.getClass().getName());          //com.sun.proxy.$Proxy100
        System.out.println(userRepo.getClass().getPackageName());   //com.sun.proxy
    }
}
