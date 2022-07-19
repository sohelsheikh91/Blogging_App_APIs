package com.springboot.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

//If Entity create kiya to corresponding table me Table bhi create honga User nam se
//table name chnage k liye @Table use kre
@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id                                     //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)         //will AUTO increment value  //for mySQL Use Identity as AUTO run's multiple queries
    private int id;
    //GenerationType.AUTO generates one more table named hibernate_sequences for maintaining the sequences.

    @Column(name ="user_name", nullable = false, length = 100)     //chnage column name in DB, Not NULL and length
    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    /*To manage the relationship between this Table 3rd Table will be used JoinTabe*/
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities =this.roles.stream()
                .map((role) ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }
    // getPassword is already Implemented
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
