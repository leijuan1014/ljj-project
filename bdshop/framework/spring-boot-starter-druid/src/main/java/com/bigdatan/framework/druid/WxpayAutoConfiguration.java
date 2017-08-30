package com.bigdatan.framework.druid;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid自动配置
 *
 */

@Configuration
@EnableConfigurationProperties(WxpayTestProperties.class)
public class WxpayAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(WxpayAutoConfiguration.class);

    @Autowired
    private WxpayTestProperties properties;

    @Bean
    public WxpayTestProperties dataSource() {
        return properties;
    }
    
}
