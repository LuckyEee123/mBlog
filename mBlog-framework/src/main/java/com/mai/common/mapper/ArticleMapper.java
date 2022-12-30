package com.mai.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mai.common.domain.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
