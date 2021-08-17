package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lim
 * @date: 2021年03月21日 14:21
 */
@Slf4j
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;//服务发现接口

    /**
     * @param payment
     * @return
     */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果为： " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,端口为： " + port, payment);
        } else {
            return new CommonResult(403, "插入数据失败,端口为： " + port);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (null != payment) {
            return new CommonResult(200, "查询成功,端口为： " + port, payment);
        } else {
            return new CommonResult(401, "查询失败,端口为： " + port);
        }

    }

    //获取服务发现
    @GetMapping("/payment/discovery")
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();//获取注册中心中注册的所有服务
        for (String service : services) {
            log.info("*********服务名： " + service);
        }
        String serviceId = "CLOUD-PAYMENT-SERVICE";//单个服务名
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);//获取单个服务下的所有实例
        for (ServiceInstance instance : instances) {
            log.info("实例： " + instance.getInstanceId() + "\t ip: " + instance.getHost() + "\t 端口: " + instance.getPort() + "\t URI: " + instance.getUri());
        }
        return discoveryClient;
    }

    /**
     * 测试自定义负载算法
     *
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return port;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
