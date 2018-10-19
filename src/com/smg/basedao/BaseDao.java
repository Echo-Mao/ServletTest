package com.smg.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.smg.util.JDBCUtil;

public abstract class BaseDao {

	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;

	// 增删改通用方法
	public int update(String sql, List<Object> temp) {
		int flag = 0;
		conn = JDBCUtil.init().getConnection();
		try {
			ps = conn.prepareStatement(sql);
			if (temp != null && temp.size() > 0) {
				for (int i = 0; i < temp.size(); i++) {
					ps.setObject(i + 1, temp.get(i));
//					System.out.println(sql);
//					System.out.println("i=" + i + ",temp is " + temp.get(i));
				}
			}
			flag = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.init().close(conn, ps, rs);
		}
		return flag;
	}

	// 查询
	@SuppressWarnings("rawtypes")
	public List search(String sql, List<Object> temp) {
		conn = JDBCUtil.init().getConnection();
//		if(conn == null) { System.out.println("conn is null"); } if(temp != null) {
//		System.out.println("temp is not null"); }
		try {
			ps = conn.prepareStatement(sql);
			if (temp != null && temp.size() > 0) {
				for (int i = 0; i < temp.size(); i++) {
					ps.setObject(i + 1, temp.get(i));
//					System.out.println("temp is "+temp.get(i));
				}
			}
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} /*
			 * finally{ if(rs == null) { System.out.println("rs is null"); } }
			 */
		return getObject(rs);
	}

	@SuppressWarnings("rawtypes")
	public abstract List getObject(ResultSet rs);

}
