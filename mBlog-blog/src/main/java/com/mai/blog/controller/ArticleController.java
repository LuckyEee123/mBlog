package com.mai.blog.controller;

import com.mai.common.domain.Article;
import com.mai.common.domain.Result;
import com.mai.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public Result getHotArticleList() {
        return articleService.hotArticleList();
    }

}
