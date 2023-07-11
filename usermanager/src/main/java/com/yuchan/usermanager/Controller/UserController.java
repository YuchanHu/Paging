package com.yuchan.usermanager.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuchan.usermanager.Pojo.PageBean;
import com.yuchan.usermanager.Pojo.User;
import com.yuchan.usermanager.Service.UserService;
import com.yuchan.usermanager.Service.Impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/findAll")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收请求参数
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        UserService userService = new UserServiceImpl();
        PageBean pageBean = userService.findUserAll(pageSize,currentPage);
        ObjectMapper om = new ObjectMapper();
        response.getWriter().append(om.writeValueAsString(pageBean));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
