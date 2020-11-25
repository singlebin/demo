package com.demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/25 15:28
 */
public interface EasyExcelService {
    void export(HttpServletResponse response);

    void importExcel(MultipartFile file);
}
