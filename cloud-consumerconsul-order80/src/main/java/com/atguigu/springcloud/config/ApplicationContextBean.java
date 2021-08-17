package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: TODO
 * @author: lim
 * @date: 2021年03月26日 22:35
 */
@Configuration
public class ApplicationContextBean {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
