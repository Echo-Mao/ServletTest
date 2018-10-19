package com.smg.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.smg.pojo.News;

public interface ServiceOfFileUpload {
    public News fileUpload(List<FileItem> fileItems);
}
