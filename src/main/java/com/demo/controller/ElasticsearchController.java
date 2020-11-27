package com.demo.controller;

import com.demo.service.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: es相关操作
 * @Author: wub
 * @Date: 2020/11/27 16:01
 */
@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class ElasticsearchController {

    private final EsService esService;

    @GetMapping("/queryGoods")
    public void queryGoods() {
        esService.queryGoods();
    }

}
