package com.yuchan.usermanager.Pojo;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
    private int pageSize;
    private int currentPage;
    private int totalCount;
    private int totalPage;
    private List<User> userList = new ArrayList<>();

    public PageBean() {
    }

    public PageBean(int pageSize, int currentPage, int totalCount, int totalPage, List<User> userList) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.userList = userList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
