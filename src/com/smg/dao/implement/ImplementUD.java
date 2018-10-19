package com.smg.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smg.basedao.BaseDao;
import com.smg.dao.UsrDao;
import com.smg.pojo.UserInfo;
import com.smg.util.JDBCUtil;

public class ImplementUD extends BaseDao implements UsrDao {

	@Override
	public UserInfo login(UserInfo user) {
		String sql = "select * from usr_table where usrName=? and usrPwd=?";
		List<Object> temp = new ArrayList<Object>();
		temp.add(user.getUsrName());
		temp.add(user.getUsrPwd());
//		System.out.println(args);
		@SuppressWarnings("unchecked")
		List<UserInfo> userInfo = search(sql, temp);
		if (userInfo.size() > 0) {
			return userInfo.get(0);
		}
		return null;
	}

    @Override
    public UserInfo checkRegisterName(String ragisterName) {
        String sql = "select * from usr_table where usrName=?";
        List<Object> temp = new ArrayList<Object>();
        temp.add(ragisterName);
        @SuppressWarnings("unchecked")
        List<UserInfo> userInfo = search(sql, temp);
        if (userInfo.size() > 0) {
            return userInfo.get(0);
        }
        return null;
    }

	@Override
	public List<UserInfo> getObject(ResultSet rs) {
		List<UserInfo> userInfo = new ArrayList<UserInfo>();
		try {
			/*
			 * if(rs == null) { System.out.println("rs is null"); }
			 */
			while (rs != null && rs.next()) {
				UserInfo user = new UserInfo();
				user.setUsrName(rs.getString("usrName"));
				user.setUsrPwd(rs.getString("usrPwd"));
				userInfo.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.init().close(conn, ps, rs);
		}
		return userInfo;
	}

}
