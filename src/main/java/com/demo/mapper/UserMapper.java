package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/25 16:00
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public List<User> selectAll();
}
