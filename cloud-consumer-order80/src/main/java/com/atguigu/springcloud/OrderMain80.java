package com.atguigu.springcloud;

import com.atguigu.myrule.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @Description: TODO
 * @author: lim
 * @date: 2021年03月21日 22:32
 */
@SpringBootApplication
@EnableEurekaClient
//启用ribbon负载规则（调用CLOUD-PAYMENT-SERVICE服务时，使用MyRibbonRule规则）
//@RibbonClient(value = "CLOUD-PAYMENT-SERVICE", configuration = MyRibbonRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
