package com.yuchan.usermanager.Service;

import com.yuchan.usermanager.Pojo.PageBean;
import com.yuchan.usermanager.Pojo.User;

import java.util.List;

public interface UserService {

    PageBean findUserAll(int pageSize, int currentPage);
}
