package com.demo.repository;

import com.demo.model.es.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/27 16:18
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Integer> {

    Goods findByProductNumber(String productNumber);
}
