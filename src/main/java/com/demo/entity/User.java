package com.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/25 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @ExcelProperty(value = "id" /*, converter = YesOrNoConvert.class*/)
    @ColumnWidth(15)
    //@NotNull(message = "是否共享不能为空！")
    private Integer id;

    @ExcelProperty(value = "姓名")
    @ColumnWidth(15)
    private String name;

    @ExcelProperty(value = "年龄")
    @ColumnWidth(15)
    private Integer age;

    @ExcelProperty(value = "性别")
    @ColumnWidth(15)
    private String sex;


}
