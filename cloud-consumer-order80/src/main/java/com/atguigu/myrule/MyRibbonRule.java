package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: ribbon负载规则
 * @author: lim
 * @date: 2021年03月28日 23:07
 */
@Configuration
public class MyRibbonRule {
    /**
     * 随机
     * @return
     */
    public IRule myRibbonRule(){
        return new RandomRule();
    }
}
