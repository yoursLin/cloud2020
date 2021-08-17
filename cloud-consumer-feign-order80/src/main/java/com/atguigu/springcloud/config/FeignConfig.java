package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: lim
 * @date: 2021年03月30日 22:45
 */
@Configuration
public class FeignConfig {
    /**
     * feignClient配置日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLever(){
        return Logger.Level.FULL;
    }
}
