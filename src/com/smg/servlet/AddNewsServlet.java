package com.smg.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.smg.pojo.News;
import com.smg.pojo.UserInfo;
import com.smg.service.ServiceOfFileUpload;
import com.smg.service.ServiceOfNews;
import com.smg.service.implement.ImplementSOF;
import com.smg.service.implement.ImplementSON;

/**
 * Servlet implementation class AddNewsServlet
 * 
 * @author Echo_Mao
 */
@WebServlet("/addNewsServlet.do")
public class AddNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @throws IOException 
     * @throws ServletException 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            /**
             * >功能由过滤器代劳
             * >统一字符编码
             * request.setCharacterEncoding("utf-8");
             * response.setCharacterEncoding("utf-8");
             */
            
            // 创建文件上传及新闻实例
            ServiceOfFileUpload sof = new ImplementSOF();
            ServiceOfNews son = new ImplementSON();
            // 获取当前用户信息
            UserInfo loginInfo = (UserInfo) request.getSession().getAttribute("loginInfo");

            // 判断是否为多部件表单
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                // 创建DiskFileItemFactory对象实例
                FileItemFactory factory = new DiskFileItemFactory();
                // 创建ServletFileUpload对象实例
                ServletFileUpload upload = new ServletFileUpload(factory);
            
                // 解析request请求中的数据
                List<FileItem> fileItems = upload.parseRequest(request); 
                News news = sof.fileUpload(fileItems);
                // 设置添加人为当前用户
                news.setAuthor(loginInfo.getUsrName());

                // 判断是否添加成功
                int flag = son.addNews(news);
                if (flag > 0) {
                    //成功,请求转发至toNewsListServlet.do
                    request.getRequestDispatcher("toNewsListServlet.do");
                } else {
                    //失败,请求转发至添加错误页面
                    request.getRequestDispatcher("Add.jsp?n_id=" + news.getN_id()
                        + "&error=" + URLEncoder.encode("添加失败!", "utf-8"));
                }
            }
            
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
