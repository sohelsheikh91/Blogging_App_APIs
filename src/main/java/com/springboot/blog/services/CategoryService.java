package com.springboot.blog.services;

import com.springboot.blog.payloads.CategoryDTO;

import java.util.List;

//created for loose coupling
public interface CategoryService {

    //create
    CategoryDTO createCategory(CategoryDTO categoryDTO);         //by dafault it is public
    //update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get
    CategoryDTO getCategory(Integer categoryId);
    //get All
    List<CategoryDTO> getAllCategory();

}
