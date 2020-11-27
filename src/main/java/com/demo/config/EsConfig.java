package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;

/**
 * @Description: elasticdaerch配置
 * @author: zzl
 * @date: 2018/12/28 15:56
 */
@Configuration
@Slf4j
@PropertySource(value = "classpath:/config/elasticsearch.yml", factory = YamlPropertySourceFactory.class)
public class EsConfig {
    /**
     * 集群IP和端口
     **/
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    /**
     * 集群名称
     */
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @Bean
    public TransportClient getTransportClient() {
        log.info("ElasticSearch初始化开始。。");
        TransportClient transportClient = null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.ping_timeout", "10s")
                    .put("client.transport.sniff", true)
                    .build();
            transportClient = new PreBuiltTransportClient(settings);

            String[] nodes = clusterNodes.split(",");


            for (String node : nodes) {
                String[] ipAndPortArray = node.split(":");
                long startTime = System.currentTimeMillis();
                transportClient.addTransportAddresses(new TransportAddress(InetAddress.getByName(ipAndPortArray[0]), Integer.parseInt(ipAndPortArray[1])));
                long end = System.currentTimeMillis() - startTime;
                log.info(String.valueOf((end)));
            }

            log.info("ElasticSearch初始化完成!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ElasticSearch初始化失败：" + e.getMessage(), e);
        }
        return transportClient;
    }

}
