package com.smg.service;

import java.util.List;

import com.smg.pojo.News;
import com.smg.pojo.Paging;
import com.smg.pojo.Search;

public interface ServiceOfNews {
    // 增
    public int addNews(News news);

    // 删
    public int delNews(int n_id);

    // 改
    public int updateNews(News news);

    // 查询所有
    public List<News> searchAllNews();

    // 根据id查询
    public News searchNewsById(int news_id);

    // 模糊查询
    public List<News> searchNewsByLike(Search search);

    // 分页查询
    public List<News> searchNewsByPaging(Paging paging, 
            String pg, Search search);

    // 符合条件的条目总数
    public int searchRowsCount(Search search);
}
