package com.mai.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mai.common.domain.Article;
import com.mai.common.domain.Result;

public interface ArticleService extends IService<Article> {
    Result hotArticleList();
}
