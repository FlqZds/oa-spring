package com.fcfz.oas.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.util.DateUtils;
import com.fcfz.oas.common.ErrInfo;
import com.fcfz.oas.common.info;
import com.fcfz.oas.entity.User;
import com.fcfz.oas.services.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//登录页面
@WebServlet(name = "loginServlet", value = "/login")
public class loginServlet extends HttpServlet {
    com.fcfz.oas.services.login login = new login();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        System.out.println("发送了登录请求，进来了     进入时间：" + DateUtils.format(new Date()));

        ErrInfo errInfo = new ErrInfo();
        info info = null;
        User user = null;
        try {
            String userName = request.getParameter("username");
            String userPwd = request.getParameter("password");

            user = login.loginUser(userName, userPwd);
//            info = new info("200", errInfo.getErrMessage(), user);
            info = new info("200", "用户登录成功", null, true);


        } catch (Exception e) {
            if (e instanceof ErrInfo) {

                errInfo = (ErrInfo) e;
                info = new info(errInfo.getCode(), errInfo.getErrMessage(), null, false);
            } else {
                info = new info("00000", "未知后台错误，请重试，或尝试联系管理员/n<br>" + errInfo.getErrMessage(), null, false);
            }
        } finally {
            response.getWriter().write(JSON.toJSONString(info));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
