/**
 * @author Echo_Mao
 * @date 10.13.2018
 */
package com.smg.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smg.basedao.BaseDao;
import com.smg.dao.NewsDao;
import com.smg.pojo.News;
import com.smg.pojo.Paging;
import com.smg.pojo.Search;
import com.smg.util.JDBCUtil;

/**
 * >新闻类数据访问对象(NewsDao.java)的实现类
 * @author Echo_Mao
 *
 */
public class ImplementND extends BaseDao implements NewsDao {

	@Override
	public List<News> getObject(ResultSet rs) {
		List<News> news = new ArrayList<News>();
//		System.out.println(rs);
		try {
			while (rs.next()) {
				News n = new News();
				n.setN_id(rs.getInt(1));
				n.setNews_id(rs.getInt(2));
				n.setTitle(rs.getString("title"));
				n.setN_context(rs.getString("n_context"));
				n.setAuthor(rs.getNString("author"));
				n.setNews_date(rs.getDate("news_date"));
				n.setNews_img_path(rs.getString("news_img_path"));
				news.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.init().close(conn, ps, rs);
		}
		return news;
	}

	/*
	 * >添加新闻
	 */
	@Override
	public int addNews(News news) {
		String sql = "insert into news_table1(news_id, title, n_context, author, news_date, news_img_path) values(?,?,?,?,to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')),?)";
		List<Object> args = new ArrayList<Object>();
		args.add(news.getNews_id());
		args.add(news.getTitle());
		args.add(news.getN_context());
		args.add(news.getAuthor());
		args.add(news.getNews_date());
		args.add(news.getNews_img_path());
		return update(sql, args);
	}

	/*
	 * >按id删除新闻
	 */
	@Override
	public int delNews(int n_id) {
		String sql = "delete news_table1 where n_id=?";
		List<Object> args = new ArrayList<Object>();
//		System.out.println(n_id);
		args.add(n_id);
		return update(sql, args);
	}

	/*
	 * >修改
	 */
	@Override
	public int updateNews(News news) {
		String sql = "update news_table1 set news_id=?, title=?, n_context=?, author=?, news_date=to_date(to_char(?,'yyyy-MM-dd')), news_img_path=?";
		List<Object> args = new ArrayList<Object>();
		args.add(news.getNews_id());
		args.add(news.getTitle());
		args.add(news.getN_context());
		args.add(news.getAuthor());
		args.add(news.getNews_date());
		args.add(news.getNews_img_path());
		return update(sql, args);
	}

	/*
	 * >查看所有新闻
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> searchAllNews() {
		String sql = "select * from news_table1 order by n_id";
		return search(sql, null);
	}

	/*
	 * >按id查找新闻
	 */
	@Override
	public News searchNewsById(int n_id) {
		String sql = "select * from news_table1 where n_id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(n_id);
		@SuppressWarnings("unchecked")
		List<News> news = search(sql, args);
		if (news.size() > 0) {
			return news.get(0);
		} else {
			return null;
		}
	}

	// 模糊查询
	@SuppressWarnings("unchecked")
	@Override
	public List<News> searchNewsByLike(Search search) {
		List<Object> temp = new ArrayList<Object>();
		String sql = "select * from news_table1 where 1=1 ";
		if (search.getsTitle() != null && !"".equals(search.getsTitle())) {
			sql += "and title like '%'||?||'%'";
			temp.add(search.getsTitle());
		}
		if (search.getsStartDate() != null) {
			sql += "and news_date >?";
			temp.add(new java.sql.Date(search.getsStartDate().getTime()));
		}
		if (search.getsEndDate() != null) {
			sql += "and news_date <?";
			temp.add(new java.sql.Date(search.getsEndDate().getTime()));
		}
		return search(sql, temp);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	@Override
	public List<News> searchNewsByPaging(Paging paging) {
		List<Object> temp = new ArrayList<Object>();
		String sql = "select * from (select t.*,rownum r from (select * from news_table1 where 1=1 ";
		if (paging.getSearch().getsTitle() != null
		        && !"".equals(paging.getSearch().getsTitle())) {
			sql += "and title like '%'||?||'%'";
			temp.add(paging.getSearch().getsTitle());
		}
		if (paging.getSearch().getsStartDate() != null) {
			sql += "and news_date >?";
			temp.add(new java.sql.Date(
			        paging.getSearch().getsStartDate().getTime()));
		}
		if (paging.getSearch().getsEndDate() != null) {
			sql += "and news_date <?";
			temp.add(new java.sql.Date(
			        paging.getSearch().getsEndDate().getTime()));
		}
		sql += ")t) where r>? and r<=?";
		temp.add(paging.getRowStart());
		temp.add(paging.getRowEnd());
		return search(sql, temp);
	}

	// 查询符合条件的总记录数
	@Override
	public int searchRowsCount(Search search) {
		int num = 0;
		conn = JDBCUtil.init().getConnection();
		List<Object> temp = new ArrayList<Object>();
		String sql = "select count(*) from news_table1 where 1=1";
		if (search.getsTitle() != null && !"".equals(search.getsTitle())) {
			sql += "and title like '%'||?||'%'";
			temp.add(search.getsTitle());
		}
		if (search.getsStartDate() != null) {
			sql += "and news_date >?";
			temp.add(new java.sql.Date(search.getsStartDate().getTime()));
		}
		if (search.getsEndDate() != null) {
			sql += "and news_date <?";
			temp.add(new java.sql.Date(search.getsEndDate().getTime()));
		}
		try {
			//System.out.println(sql);
			ps = conn.prepareStatement(sql);
			if (temp != null && temp.size() > 0) {
				for (int i = 0; i < temp.size(); i++) {
					ps.setObject(i + 1, temp.get(i));
				}
			}
			rs = ps.executeQuery();
			rs.next();
			num = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.init().close(conn, ps, rs);
		}
		return num;
	}
}
