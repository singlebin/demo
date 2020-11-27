package com.demo.service.impl;

import com.demo.model.es.Goods;
import com.demo.repository.GoodsRepository;
import com.demo.service.EsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/27 16:02
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EsServiceImpl implements EsService {

    private final GoodsRepository goodsRepository;

    @Override
    public void queryGoods() {
        Goods build = Goods.builder()
                .id(1L)
                .brandCode("DD")
                .brandName("大东")
                .productName("2019年春季单鞋")
                .seasonCode("C")
                .productNumber("DW19C2308A")
                .supplierCode("DD")
                .supplierName("大东")
                .yearCode("2019")
                .build();
        goodsRepository.save(build);
        Goods goods = goodsRepository.findByProductNumber("DW19C2308A");
        System.out.println("goods = " + goods);
    }
}
