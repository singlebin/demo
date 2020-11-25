package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;

import java.util.List;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/25 16:10
 */
public interface UserService extends IService<User> {

    public List<User> findUserAll();
}
