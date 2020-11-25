package com.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/25 16:21
 */
@AllArgsConstructor
public enum IsSharedEnum {

    /**
     * 是否共享
     */
    YES(1, "是"),
    NO(0, "否");

    @Getter
    public final Integer key;

    @Getter
    public final String value;

    public static String getValue(Integer key) {
        IsSharedEnum[] values = IsSharedEnum.values();
        for (IsSharedEnum value : values) {
            if (value.key.equals(key)) {
                return value.value;
            }
        }
        return null;
    }

    public static Integer getKey(String val) {
        IsSharedEnum[] values = IsSharedEnum.values();
        for (IsSharedEnum value : values) {
            if (value.value.equals(val)) {
                return value.key;
            }
        }
        return null;
    }
}
