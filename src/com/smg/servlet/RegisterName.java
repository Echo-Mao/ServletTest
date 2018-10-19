package com.smg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.pojo.UserInfo;
import com.smg.service.ServiceOfUsr;
import com.smg.service.implement.ImplementSOU;

/**
 * @author Echo_Mao
 * Servlet implementation class RegisterName
 */
@WebServlet("/registerName.do")
public class RegisterName extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取request对象中的registerName
        String registerName = request.getParameter("registerName");
        //创建sou实例
        ServiceOfUsr sou = new ImplementSOU();
        //调用checkRegisterName()方法查询registerName是否存在于数据表记录中,并返回UserInfo对象
        UserInfo registerInfo = sou.checkRegisterName(registerName);
        String message = "";
        if (registerInfo == null) {
            //为空,说明不重复,可以使用该名称
            message = "{\"message\":true}";
        } else {
            //不为空,说明重复,不能使用
            message = "{\"message\":false}";
        }
        //后台通过响应输出判断结果
        PrintWriter pw = response.getWriter();
        pw.write(message);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
