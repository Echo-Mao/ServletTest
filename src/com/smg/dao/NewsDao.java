package com.smg.dao;

import java.util.List;

import com.smg.pojo.News;
import com.smg.pojo.Paging;
import com.smg.pojo.Search;


public interface NewsDao {
	
	/**
	 * >增
	 * @param news
	 * @return
	 */
	public int addNews(News news);
	
	/**
	 * >删
	 * @param news_id
	 * @return
	 */
	public int delNews(int news_id);
	
	/**
	 * >改
	 * @param news
	 * @return
	 */
	public int updateNews(News news);
	
	/**
	 * >查询所有新闻
	 * @return
	 */
	public List<News> searchAllNews();
	
	/**
	 * >根据id查询
	 * @param news_id
	 * @return
	 */
	public News searchNewsById(int news_id);
	
	/**
	 * >模糊查询
	 * @param search
	 * @return
	 */
	public List<News> searchNewsByLike(Search search);
	
	/**
	 * >分页查询
	 * @param paging
	 * @return
	 */
	public List<News> searchNewsByPaging(Paging paging);
	
	/**
	 * >获取符合查询条件的条目总数
	 * @param search
	 * @return
	 */
	public int searchRowsCount(Search search);
	
}