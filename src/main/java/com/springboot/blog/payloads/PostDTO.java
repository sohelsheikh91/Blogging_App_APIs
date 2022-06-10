package com.springboot.blog.payloads;

import com.springboot.blog.entities.Category;
import com.springboot.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    @NotEmpty
    @Size(min = 5, message = "Title must be of min length 5")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Content must be of min length 10" )
    private String content;

    private String imageName;
    private Date addedDate;

    private CategoryDTO category;   //Use DTO as it doesn't have List<Post> posts , otherwise it will cause infinite Recursion with category ->post ->category
    // Dont use @ManyToOne or @Column name here , Only Validations we do in DTO
    private UserDto user;
}
