package com.demo.util;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/27 17:11
 */
@Configuration
public class RabbitmqConstants {

    /**
     * 队列名称
     */
    public static final String QUEUE_TEST = "queue.test.4";

    /**
     * 设置路由key
     */
    public static final String ROUTINGKEY_A = "queue.test.4";

    /**
     * 交换空间名称(点对点)
     */
    public static final String DIRECT_EXCHANGE = "exchange.direct";
    /**
     * 交换空间名称(广播)
     */
    public static final String FANOUT_EXCHANGE = "exchange.fanout";

    /**
     * 声明队列名称(点对点)
     *
     * @return
     */
    @Bean(name = "queueA")
    public Queue queueA() {
        return new Queue(QUEUE_TEST);
    }

    /**
     * 将队列通过路由key到绑定交互机上(点对点)
     *
     * @param exchange
     * @param queueA
     * @return
     */
    @Bean
    public Binding bindingExchangeQueueA(DirectExchange exchange, @Qualifier("queueA") Queue queueA) {
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTINGKEY_A);
    }

    /**
     * 点对点模式
     *
     * @return
     */
    @Bean
    public DirectExchange getDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    /**
     * 广播模式
     *
     * @return
     */
    @Bean
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }

}
