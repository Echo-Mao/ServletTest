package com.smg.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.pojo.UserInfo;
import com.smg.service.ServiceOfUsr;
import com.smg.service.implement.ImplementSOU;

/**
 * Servlet implementation class LoginServlet
 * 
 * @author Echo_Mao
 */
@WebServlet("/loginServlet.do")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /**
         * >功能由过滤器代劳
         * >统一字符编码
         * request.setCharacterEncoding("utf-8");
         * response.setCharacterEncoding("utf-8");
         */

        // 获取用户信息
        String usrName = request.getParameter("usrName");
        String usrPwd = request.getParameter("usrPwd");

        String rembMe = request.getParameter("rembMe");
        // 将得到的用户信息写入loginInfo对象
        UserInfo loginInfo = new UserInfo();
        loginInfo.setUsrName(usrName);
        loginInfo.setUsrPwd(usrPwd);
        System.out.println("LoginInfo:" + loginInfo);

        ServiceOfUsr sou = new ImplementSOU();
        loginInfo = sou.login(loginInfo);
        if (loginInfo != null) {
            if ("yes".equals(rembMe)) {
                // 声明Cookie
                Cookie nameCookie = new Cookie("nameCookie",
                        URLEncoder.encode(loginInfo.getUsrName(), "utf-8"));
                Cookie pwdCookie = new Cookie("pwdCookie",
                        URLEncoder.encode(loginInfo.getUsrPwd(), "utf-8"));
                // 设置保存位置
                nameCookie.setPath("/NewsServlet");
                pwdCookie.setPath("/NewsServlet");
                // 设置寿命
                nameCookie.setMaxAge(60 * 60 * 24 * 10);
                pwdCookie.setMaxAge(60 * 60 * 24 * 10);
                // 对客户端给出响应
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            // 登录成功后暂时保存用户信息
            request.getSession().setAttribute("loginInfo", loginInfo);
            // 请求转发
//              request.getRequestDispatcher("Main.jsp").forward(request, response);
            // 重定向
            response.sendRedirect("toNewsListServlet.do");
        } else {
            // 重定向
            request.getRequestDispatcher("Error.jsp");
//                response.sendRedirect("Login.jsp?error="
//                        + URLEncoder.encode("用户名或密码错误!", "utf-8"));
        }
    }

    /**
     * @throws IOException 
     * @throws ServletException 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
