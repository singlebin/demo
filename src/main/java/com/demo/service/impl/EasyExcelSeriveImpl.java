package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.EasyExcelService;
import com.demo.util.EasyExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/25 15:29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EasyExcelSeriveImpl implements EasyExcelService {

    private final UserMapper userMapper;

    @Override
    public void export(HttpServletResponse response) {
        User user = userMapper.selectById(1);
        List<User> users = new ArrayList<>();
        users.add(user);
        EasyExcelUtil.export(response, "用户信息", "用户信息", users, User.class);
    }

    @Override
    public void importExcel(MultipartFile file) {
        List<User> users = EasyExcelUtil.readExcel(file, User.class);
        users.forEach(userMapper::insert);
        System.out.println("users = " + users);
    }
}
