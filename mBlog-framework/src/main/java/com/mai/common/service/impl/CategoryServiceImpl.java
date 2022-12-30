package com.mai.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.common.Constants.SystemConstants;
import com.mai.common.domain.Article;
import com.mai.common.domain.Category;
import com.mai.common.domain.Result;
import com.mai.common.domain.vo.CategoryVO;
import com.mai.common.mapper.CategoryMapper;
import com.mai.common.service.ArticleService;
import com.mai.common.service.CategoryService;
import com.mai.common.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author mai
 * @since 2022-12-30 14:28:15
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public Result getCategoryList() {
        // 查询文章表，状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 获取文章的分类id且去重
        List<Article> articleList = articleService.list(articleWrapper);
        Set<Long> categoryIds = articleList
                .stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        // 查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装vo
        List<CategoryVO> categoryVOS = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);
        return Result.success(categoryVOS);
    }
}

