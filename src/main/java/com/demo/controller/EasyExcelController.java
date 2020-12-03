package com.demo.controller;

import com.demo.service.EasyExcelService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: easyExcel相关操作
 * @Author: wub
 * @Date: 2020/11/17 16:02
 */
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
@Api(tags = "easyExcel相关操作接口")
public class EasyExcelController {

    private final EasyExcelService easyExcelService;

    /**
     * 导出
     */
    @GetMapping("/export")
    @ApiOperation("导出")
    public String export(HttpServletResponse response) {
        easyExcelService.export(response);
        return "OK";
    }

    /**
     * 导入
     */
    @GetMapping("/importExcel")
    @ApiOperation("导入")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        easyExcelService.importExcel(file);
        return "OK";
    }

}
