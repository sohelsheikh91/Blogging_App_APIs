package com.springboot.blog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Content;

    @ManyToOne
    @JoinColumn(name = "post_id")    //post_post_id column was auto generating, now given name using this
    private Post post;

}
