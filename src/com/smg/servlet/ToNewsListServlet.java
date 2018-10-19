package com.smg.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.pojo.News;
import com.smg.pojo.Paging;
import com.smg.pojo.Search;
import com.smg.service.ServiceOfNews;
import com.smg.service.implement.ImplementSON;

/**
 * Servlet implementation class ToNewsListServlet
 * 
 * @author Echo_Mao
 */
@WebServlet("/toNewsListServlet.do")
public class ToNewsListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServiceOfNews son = new ImplementSON();

        // 按条件搜索
        String sTitle = "";
        if (request.getParameter("sTitle") != null) {
            sTitle = request.getParameter("sTitle");
        }
        String sStartDate = "";
        if (request.getParameter("sStartDate") != null) {
            sStartDate = request.getParameter("sStartDate");
        }
        String sEndDate = "";
        if (request.getParameter("sEndDate") != null) {
            sEndDate = request.getParameter("sEndDate");
        }
        // 将值赋予search
        Search search = new Search();
        search.setsTitle(sTitle);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (sStartDate != null && !"".equals(sStartDate)) {
                search.setsStartDate(sdf.parse(sStartDate));
            }
            if (sEndDate != null && !"".equals(sEndDate)) {
                search.setsEndDate(sdf.parse(sEndDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 处理分页对象
        Paging paging = new Paging();
        String pg = request.getParameter("pg");

        // 查询分页后的新闻列表
        List<News> news = son.searchNewsByPaging(paging, pg, search);
        // 向页面保存数据
        request.setAttribute("news", news);
        request.setAttribute("paging", paging);
        // 跳转页面
        request.getRequestDispatcher("Main.jsp").forward(request, response);
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
