package com.smg.service.implement;

import java.util.List;

import com.smg.dao.NewsDao;
import com.smg.dao.implement.ImplementND;
import com.smg.pojo.News;
import com.smg.pojo.Paging;
import com.smg.pojo.Search;
import com.smg.service.ServiceOfNews;

public class ImplementSON implements ServiceOfNews {
	private NewsDao nd = new ImplementND();

	@Override
	public int addNews(News news) {
		return nd.addNews(news);
	}

	@Override
	public int delNews(int n_id) {
		return nd.delNews(n_id);
	}

	@Override
	public int updateNews(News news) {
		return nd.updateNews(news);
	}

	@Override
	public List<News> searchAllNews() {
		return nd.searchAllNews();
	}

	@Override
	public News searchNewsById(int news_id) {
		return nd.searchNewsById(news_id);
	}

	@Override
	public List<News> searchNewsByLike(Search search) {
		return nd.searchNewsByLike(search);
	}

	@Override
	public List<News> searchNewsByPaging(Paging paging, 
	        String pg, Search search) {
	    //处理分页对象
	    if(pg == null || "".equals(pg)) {
	        paging.setPage(1);
	    } else {
	        paging.setPage(new Integer(pg));
	    }
	    //设置符合搜索条件的总记录数
	    paging.setRowsCount(searchRowsCount(search));
	    //设置查询条件
	    paging.setSearch(search);
	    //调用DAO
		return nd.searchNewsByPaging(paging);
	}

	@Override
	public int searchRowsCount(Search search) {
		return nd.searchRowsCount(search);
	}

}
