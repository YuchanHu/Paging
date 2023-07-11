package com.yuchan.usermanager.DAO.Impl;

import com.yuchan.usermanager.DAO.UserDao;
import com.yuchan.usermanager.Pojo.User;
import com.yuchan.usermanager.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll(int pageSize,int currentPage) {
        String sql = "SELECT * FROM user LIMIT ?,?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet res = null;
        List<User> userList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,(currentPage-1)*pageSize);
            ps.setInt(2,pageSize);
            res = ps.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId((res.getInt("id")));
                user.setName(res.getString("name"));
                user.setPwd(res.getString("pwd"));
                user.setEmail(res.getString("email"));
                user.setBirthday(res.getString("birthday"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(res);
            JDBCUtil.close(ps);
            JDBCUtil.close(conn);
        }
        return userList;
    }

    @Override
    public int findCount() {
        String sql = "select count(*) from user";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet res = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            if (res.next()) {
                count= res.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(res);
            JDBCUtil.close(ps);
            JDBCUtil.close(conn);
        }
        return count;
    }
}
