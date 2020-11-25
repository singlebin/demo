package com.demo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @description: easyExcel封装的方法
 * @Link {https://github.com/alibaba/easyexcel}
 * @Author: wub
 * @Date: 2020/11/25 15:32
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 读取excel
     *
     * @param file  文件
     * @param clazz class
     */
    public static <T> List<T> readExcel(MultipartFile file, Class<T> clazz) {
        ZipSecureFile.setMinInflateRatio(-1.0d);
        List<T> list = new ArrayList<>();
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EasyExcel.read(inputStream, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T o, AnalysisContext analysisContext) {
                list.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                //
            }
        }).sheet().doRead();
        return list;
    }

    /**
     * @param response
     * @param fileName
     * @param sheetName
     * @param data
     * @param clazz
     */
    public static void export(HttpServletResponse response, String fileName, String sheetName, List data, Class<?> clazz) {
        try {
            response.setContentType("application/force-download");
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] textByte = fileName.getBytes(StandardCharsets.UTF_8);
            String fn = encoder.encodeToString(textByte);
            response.addHeader("Content-Disposition", "attachment;fileName=" + fn + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet(sheetName)
                    .registerWriteHandler(new EasyExcelPercentWriteHandler())
                    .doWrite(data);
        } catch (Exception e) {
            log.error("导出出错", e);
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
        }
    }
}
