package com.springboot.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title", length = 100, nullable = false)
    private String categoryTitle;
    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    //mappedBy Column - Category name defined in Post
    //cascade - if we remove parent then child should also be removed and If parent added then child should be saved automatically
    //fetchType - if you want to remove parent and dont want child to be removed
    private List<Post> posts = new ArrayList<>();

}
