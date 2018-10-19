package com.smg.service.implement;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import com.smg.pojo.News;
import com.smg.service.ServiceOfFileUpload;

/**
 * 
 * @author Echo_Mao
 *
 */
public class ImplementSOF implements ServiceOfFileUpload {

    @Override
    public News fileUpload(List<FileItem> fileItems) {
        News news = new News();
        //遍历从表现层传来的表单数据
        for (FileItem item : fileItems) {
            try {
                //判断是否为普通表单元素
                if (item.isFormField()) {
                    //通过name获取各标签的值
                    String inputName = item.getFieldName();
                    //获取新闻编号并赋值
                    if (inputName.equals("newsId")) {
                        news.setNews_id(new Integer(item.getString("utf-8")));
                    //获取新闻标题并赋值
                    } else if (inputName.equals("newsTitle")) {
                        news.setTitle(item.getString("utf-8"));
                    //获取新闻内容并赋值
                    } else if (inputName.equals("newsContext")) {
                        news.setN_context(item.getString("utf-8"));
                    //获取新闻日期并赋值
                    } else if (inputName.equals("newsDate")) {
                        if (item.getString("utf-8") != null
                                && !"".equals(item.getString("utf-8"))) {
                            SimpleDateFormat sdf =
                                    new SimpleDateFormat("yyyy-MM-dd");
                            news.setNews_date(sdf.parse(
                                    item.getString("utf-8")));
                        }
                    }
                //非普通表单元素
                } else {
                    //获取原始文件名
                    String originalName = item.getName();
                    //判断是否有叫此名的文件
                    if (originalName != null && !"".equals(originalName)){
                        //使用UUID(Universally Unique Identifier)给文件创建一个新名
                        String newName = UUID.randomUUID().toString()
                            + originalName.substring(
                                    originalName.indexOf("."));
                        //指定新文件的绝对路径
                        File newFile = new File(
                            "D:/Tomcat/apache-tomcat-9.0.12/webapps/img/"
                            + newName);
                        //输出(复制)该新文件到指定目录
                        item.write(newFile);
                        //设置新闻图片路径
                        news.setNews_img_path("../img/" + newName);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return news;
    }
}
