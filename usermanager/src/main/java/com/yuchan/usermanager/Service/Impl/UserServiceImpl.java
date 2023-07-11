package com.yuchan.usermanager.Service.Impl;

import com.yuchan.usermanager.DAO.UserDao;
import com.yuchan.usermanager.DAO.Impl.UserDaoImpl;
import com.yuchan.usermanager.Pojo.PageBean;
import com.yuchan.usermanager.Pojo.User;
import com.yuchan.usermanager.Service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public PageBean findUserAll(int pageSize, int currentPage) {
        UserDao userDao = new UserDaoImpl();
        //分页列表
        List<User> userList = userDao.findAll(pageSize, currentPage);
        //总记录数
        int totalCount = userDao.findCount();
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        //封装PageBean
        PageBean pageBean = new PageBean(pageSize,currentPage,totalCount,totalPage,userList);
        return pageBean;
    }
}
