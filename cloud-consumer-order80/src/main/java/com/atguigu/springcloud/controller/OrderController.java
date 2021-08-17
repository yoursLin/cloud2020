package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Description: TODO
 * @author: lim
 * @date: 2021年03月21日 22:22
 */
@RestController
@Slf4j
public class OrderController {
    //    private static final String PAYMENTURL = "http://localhost:8001"; //单机版，地址写死
//集群版：调用服务名，但restTemplate需要加上注解@LoadBalanced来使用负载均衡机制
    private static final String PAYMENTURL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> createPayment(Payment payment) {
        CommonResult result = restTemplate.postForObject(PAYMENTURL + "/payment/create", payment, CommonResult.class);
        log.info("创建结果： " + result);
        return result;
    }

    /**
     * restTemplate.getForObject 返回对象为响应体中数据转换成的对象，基本上可以理解为json
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        CommonResult<Payment> payment = restTemplate.getForObject(PAYMENTURL + "/payment/get/" + id, CommonResult.class);
        log.info("查询结果：" + payment);
        return payment;
    }

    @GetMapping(value = "/consumer/payment/postEntity")
    public CommonResult<Payment> postEntity(Payment payment) {
        ResponseEntity<CommonResult> postEntity = restTemplate.postForEntity(PAYMENTURL + "/payment/create", payment, CommonResult.class);
        if (postEntity.getStatusCode().is2xxSuccessful()) {
            return postEntity.getBody();
        } else {
            return new CommonResult<>(444, "创建失败");
        }
    }

    /**
     * restTemplate.getForEntity 返回对象为ResponseEntity对象，包含了响应中的一些重要信心，比如响应头、响应状态码、响应体登
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENTURL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t " + entity.getHeaders());
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "查询失败");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");//注册中心中所有UP状态的服务
        if (instances == null || instances.size() <= 0) {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);//通过自定义算法获取服务
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", String.class);

    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject(PAYMENTURL + "/payment/zipkin/", String.class);
        return result;
    }
}
