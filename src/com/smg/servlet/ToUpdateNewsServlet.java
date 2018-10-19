package com.smg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.pojo.News;
import com.smg.service.ServiceOfNews;
import com.smg.service.implement.ImplementSON;

/**
 * Servlet implementation class ToUpdateNewsServlet
 * 
 * @author Echo_Mao
 */
@WebServlet("/toUpdateNewsServlet.do")
public class ToUpdateNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取id
        String nId = request.getParameter("n_id");
        //创建son实例
        ServiceOfNews son = new ImplementSON();
        //查询并返回想要修改的新闻纪录
        News updateNews = son.searchNewsById(new Integer(nId));
        //在定义域中写入信息
        request.setAttribute("updateNews", updateNews);
        
        //跳转到修改页面
        request.getRequestDispatcher("Update.jsp").forward(request, response);
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
