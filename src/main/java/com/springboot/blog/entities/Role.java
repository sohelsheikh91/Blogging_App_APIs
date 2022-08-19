package com.springboot.blog.entities;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

}
