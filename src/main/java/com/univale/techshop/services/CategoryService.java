package com.univale.techshop.services;

import com.univale.techshop.entities.Category;
import com.univale.techshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findOne( Long categoryId ) {

        return categoryRepository.findById(categoryId).orElseThrow();
    }
}
