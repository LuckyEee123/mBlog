package com.mai.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.common.domain.Article;
import com.mai.common.domain.Result;
import com.mai.common.domain.vo.HotArticleVO;
import com.mai.common.mapper.ArticleMapper;
import com.mai.common.service.ArticleService;
import com.mai.common.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.mai.common.Constants.SystemConstants.ARTICLE_STATUS_NORMAL;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public Result hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL)
                    .orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        List<HotArticleVO> articlesVO = BeanCopyUtils.copyBeanList(articles, HotArticleVO.class);
//        for (Article article : articles) {
//            HotArticleVO vo = new HotArticleVO();
//            BeanUtils.copyProperties(article, vo);
//            articlesVO.add(vo);
//        }
        return Result.success(articlesVO);
    }
}
