package com.smg.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.pojo.UserInfo;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginCheckFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        System.out.println("Login check filter was destroyed.");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        // 拼接动态绝对路径,协议名+服务器域名+服务器端口号+项目根路径
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
        // 获取资源路径
        String path = request.getServletPath();

        // 从session中获取登录信息
        UserInfo loginInfo = (UserInfo) request.getSession().getAttribute("loginInfo");
        
        if (loginInfo != null || "/Login.jsp".equals(path)
                || "/loginServlet.do".equals(path) || "/SignUp.jsp".equals(path)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(basePath + "Login.jsp?error="
                    + URLEncoder.encode("非法请求!请先登录!", "utf-8"));
            System.out.println("拦截到来自 " + request.getLocalName() + " 的非法访问请求:" + path);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("Login check filter was created.");
    }

}
