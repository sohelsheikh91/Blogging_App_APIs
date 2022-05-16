package com.springboot.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer categoryId;
    @NotEmpty
    @Size(min= 3, message = "Min size of Category Title is 4")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10, message = "Min size of Category Description is 4")
    private String categoryDescription;
}
