package com.demo.model.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/27 16:03
 */
@Data
@Document(indexName = "goods1-index", type = "goodsInfo1", replicas = 3)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    @Id
    private Long id;
    /**
     * 商品编码
     */
    private String productNumber;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 品牌
     */
    private String brandCode;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 供应商code
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 年份
     */
    private String yearCode;
    /**
     * 季节
     */
    private String seasonCode;
}
