package com.mai.blog.controller;

import com.mai.common.domain.Result;
import com.mai.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categoryList")
    public Result getCategoryList() {
        return categoryService.getCategoryList();
    }

}
