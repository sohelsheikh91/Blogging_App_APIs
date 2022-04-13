package com.springboot.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//If Entity create kiya to corresponding table me Table bhi create honga User nam se
//table name chnage k liye @Table use kre
@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id                                     //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)         //will AUTO increment value  //for mySQL Use Identity as AUTO run's multiple queries
    private int id;
    //GenerationType.AUTO generates one more table named hibernate_sequences for maintaining the sequences.

    @Column(name ="user_name", nullable = false, length = 100)     //chnage column name in DB, Not NULL and length
    private String name;

    private String email;

    private String password;

    private String about;

}
