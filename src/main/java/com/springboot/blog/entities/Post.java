package com.springboot.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_title", length = 100, nullable = false)
    private String title;
    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false) // Validations are in DTO class , but mappings and Column names are in Entity Class
    private Category category;
    @ManyToOne
    private User user;
    /*
    JPA will do all the work-
    Hibernate: alter table post add column category_category_id integer
    Hibernate: alter table post add column user_id integer
    Hibernate: alter table post add constraint FKr7g7up9a3358c3sycj8ihw41v foreign key (category_category_id) references categories (category_id)
    Hibernate: alter table post add constraint FK7ky67sgi7k0ayf22652f7763r foreign key (user_id) references users (id)
    */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}
