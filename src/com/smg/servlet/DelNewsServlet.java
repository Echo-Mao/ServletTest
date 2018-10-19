package com.smg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.service.ServiceOfNews;
import com.smg.service.implement.ImplementSON;

/**
 * Servlet implementation class DelNewsServlet
 * 
 * @author Echo_Mao
 */
@WebServlet("/delNewsServlet.do")
public class DelNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @throws IOException
     * @throws ServletException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 获取id
        String nId = request.getParameter("n_id");
        // 创建son实例
        ServiceOfNews son = new ImplementSON();
        // 删除指定纪录
        int flag = son.delNews(new Integer(nId));
        if (flag > 0) {
            // 成功,重定向至toNewsListServlet.do
            response.sendRedirect("toNewsListServlet.do");
            System.out.println("news "+nId+" was deleted");
        } else {
            response.sendRedirect("Error.jsp");
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
