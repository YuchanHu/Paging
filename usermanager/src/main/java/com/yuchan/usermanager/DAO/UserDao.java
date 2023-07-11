package com.yuchan.usermanager.DAO;

import com.yuchan.usermanager.Pojo.User;

import java.util.List;

public interface UserDao {
    List<User> findAll(int pageSize,int currentPage);
    int findCount();
}
