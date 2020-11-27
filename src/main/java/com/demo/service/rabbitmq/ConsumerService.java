package com.demo.service.rabbitmq;

import com.demo.entity.User;
import com.demo.util.RabbitmqConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/27 17:00
 */
@Component
@EnableRabbit
@Slf4j
public class ConsumerService {

    private final ObjectMapper mapper = new ObjectMapper();


    /**
     *  监听字符串类型的消息
     */
//     注解版的监听（声明了路由键、队列和交互机），监听的队列自动通过路由键  绑定交互机上
//    @RabbitListener(
//            bindings = @QueueBinding(
//                exchange = @Exchange(value = RabbitmqConstants.DIRECT_EXCHANGE),
//                value = @Queue(value = RabbitmqConstants.QUEUE_TEST,durable = "true"),
//                key = RabbitmqConstants.ROUTINGKEY_A),containerFactory = "singleListenerContainer")


//    如果没用注解版的监听（需要自己去配置文件配置）
//    @RabbitListener(queues = RabbitmqConstants.QUEUE_TEST)
//    public void reviceRabbitmqStringMessage(Channel channel, Message message){
//        try {
//            String result = new String(message.getBody(), UTF_8);
//            System.out.println("result = " + result);
//        } catch (Exception e) {
//            log.error("", e);
//            try {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            } catch (IOException e1) {
//                log.error("", e1);
//            }
//        }
//    }

    /**
     * 监听对象类型的消息
     */
    @RabbitListener(queues = RabbitmqConstants.QUEUE_TEST)
    public void reviceRabbitmqObjectMessage(Channel channel, Message message) {
        try {
            User user = mapper.readValue(message.getBody(), User.class);
            System.out.println("user = " + user);
        } catch (Exception e) {
            log.error("", e);
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e1) {
                log.error("", e1);
            }
        }
    }
}
