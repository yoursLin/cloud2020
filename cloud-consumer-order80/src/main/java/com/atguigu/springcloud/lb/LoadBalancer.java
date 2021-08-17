package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Description: 自定义负载算法接口类
 * @author: lim
 * @date: 2021年03月29日 21:50
 */
public interface LoadBalancer {
    /**
     * 获取所有up的服务
     * @param serviceInstances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
