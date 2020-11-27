package com.demo.controller;

import com.demo.entity.User;
import com.demo.util.RabbitmqConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/27 16:55
 */
@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor
public class RabbitmqController {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送字符串类型消息
     */
    @GetMapping("/sendString")
    public void sendString() {
        rabbitTemplate.convertAndSend(RabbitmqConstants.DIRECT_EXCHANGE, RabbitmqConstants.ROUTINGKEY_A, "123456");
    }

    /**
     * 发送字对象消息
     */
    @GetMapping("/sendObject")
    public void sendObject() {
        rabbitTemplate.convertAndSend(
                RabbitmqConstants.DIRECT_EXCHANGE,
                RabbitmqConstants.ROUTINGKEY_A,
                User.builder().age(11).id(1).name("test").sex("男").build());
    }
}
