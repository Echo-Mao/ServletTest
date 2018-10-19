package com.smg.servlet;

import java.io.IOException;
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
 * Servlet implementation class UpdateNewsServlet
 */
@WebServlet("/updateNewsServlet.do")
public class UpdateNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        try {
        
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
            
                try {
                    // 解析request请求中的数据
                    List<FileItem> fileItems = upload.parseRequest(request);
                    // 获取解析出的数据
                    News news = sof.fileUpload(fileItems);
                    // 设置修改人为当前用户
                    news.setAuthor(loginInfo.getUsrName());
                    // 判断是否修改成功
                    int flag = son.updateNews(news);
                    if (flag > 0) {
                        // 成功,请求转发至toNewsListServlet.do
                        request.getRequestDispatcher("toNewsListServlet.do");
                    } else {
                        // 失败,跳转至错误页面
                        request.getRequestDispatcher("Update.jsp?n_id=" 
                                + news.getN_id() + "&error="
                                + URLEncoder.encode("修改失败!", "utf-8"));
                    }
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
            }
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @throws IOException 
     * @throws ServletException 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
