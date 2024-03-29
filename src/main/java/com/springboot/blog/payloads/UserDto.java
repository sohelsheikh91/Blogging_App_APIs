package com.springboot.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.blog.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    // UserDto will have only fields which are receiving from user end
    //Exposing directly to API's

    //JPA is Specification and Hibernate is ORM implementation
    //Hibernate Validator is implementation of validation API - (JSR 380 known as Bean Validator 2.0 which validates JAva beans)

    @NotNull
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Name must be of min length 4")
    private String name;

    @Email(message = "Email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10, message = "Password must be min of 3 chars and max of 10 chars")
    //@Pattern(regexp="^[a-zA-Z0-9]{8}",message="Must contain smallcase , uppercase and number")
    //@JsonIgnore                 //To Hide Password, but it Will ignore for both GET(Marshalling) and POST(UnMarshalling)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)   //To hide password only for Marshalling
    private String password;

    @NotEmpty
    private String about;

    private Set<Role> roles = new HashSet<>();   //Role used as Role doesnt has @Manytomany mapping other need to create RoleDTO class
}
