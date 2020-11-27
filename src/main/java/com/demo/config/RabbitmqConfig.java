package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/**
 * @description: RabbitmqConfig
 * @Author: wub
 * @Date: 2018/12/27 9:46
 */
@Configuration
@Slf4j
@PropertySource(value = "classpath:/config/rabbitmq.yml", factory = YamlPropertySourceFactory.class)
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.address}")
    private String address;

    /**
     * Rabbitmq 连接工厂
     *
     * @return
     */
    @Bean(name = "adapterConnectionFactory")
    public ConnectionFactory adapterConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        //消息确认机制confirm-callback或return-callback,成功后confirm,失败后回调
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 实例化 RabbitTemplate对象
     *
     * @return
     */
    @Bean(name = "adapterRabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(adapterConnectionFactory());
        //exchange根据路由键匹配不到对应的queue时将会调用basic.return将消息返还给生产者
        rabbitTemplate.setMandatory(true);
        //转换json序列化
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //消息成功发送到broker
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("mq message send (ACK)status = {}", ack);
        });
        //消息发送失败
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;
    }

    /**
     * 消费者监听
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(adapterConnectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //单台并发消费者数量
        factory.setConcurrentConsumers(10);
        //单台并发消费的最大消费者数量
        factory.setMaxConcurrentConsumers(30);
        //预取消费数量,unacked数量超过这个值broker将不会接收消息
        factory.setPrefetchCount(50);
        //有事务时处理的消息数
        factory.setTxSize(1);
        //消息确认机制
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //构建retryConfig,用于在JavaConfig的模式下读取并发参数
        //待定
        return factory;
    }
}
