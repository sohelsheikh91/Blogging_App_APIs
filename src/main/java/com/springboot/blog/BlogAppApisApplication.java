package com.springboot.blog;

import com.springboot.blog.config.AppConstants;
import com.springboot.blog.entities.Role;
import com.springboot.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    //used as Password Encrypter, to Generated encrypted password
    @Override
    public void run(String... args) throws Exception {
        //System.out.println(this.passwordEncoder.encode("4321"));

        try{

            Role role = new Role();
            role.setId(AppConstants.ADMIN_USER);
            role.setName("ROLE_ADMIN");

            Role role1 = new Role();
            role1.setId(AppConstants.NORMAL_USER);
            role1.setName("ROLE_USER");

            List<Role> roles = List.of(role, role1);
            this.roleRepo.saveAll(roles);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
