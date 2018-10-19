package com.smg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	private Connection conn;
	private static JDBCUtil ju;

	private JDBCUtil() {
		try {
			Class.forName(PropertiesUtil.init().getValue("jdbc.driver"));// 加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public synchronized static JDBCUtil init() {
		if (ju == null) {
			ju = new JDBCUtil();
		}
		return ju;
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(PropertiesUtil.init().getValue("jdbc.url"), PropertiesUtil.init().getValue("jdbc.name"), PropertiesUtil.init().getValue("jdbc.password")); // 获取数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {

		// 先开后关
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
