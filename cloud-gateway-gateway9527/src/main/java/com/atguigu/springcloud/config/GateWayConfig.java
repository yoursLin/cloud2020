package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 编码实现路由规则
 * @author: lim
 * @date: 2021年04月08日 21:49
 */
@Configuration
public class GateWayConfig {
    /**
     * 配置ID为 routeId的路由规则，当访问地址http://localhost:9527/path时gateway会自动 转发到 uri
     * 比如：配置了一个ID为 path_route_atguigu的路由规则
     * 当访问地址http://localhost:9527/guonei时gateway会自动 转发到 https://news.baidu.com/guonei
     * @param routeLocatorBuilder 路由构建器
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //配置 /guonei 路由规则
        routes.route("path_route_atguigu_guonei",r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        //配置 /guoji 路由规则
        routes.route("path_route_atguigu_guoji",r -> r.path("/guoji").uri("https://news.baidu.com/guoji")).build();
        //配置 /mil 路由规则
        routes.route("path_route_atguigu_mil",r -> r.path("/mil").uri("https://news.baidu.com/mil")).build();
        //配置 /finance 路由规则
        routes.route("path_route_atguigu_finance",r -> r.path("/finance").uri("https://news.baidu.com/finance")).build();
        //配置 /ent 路由规则
        routes.route("path_route_atguigu_ent",r -> r.path("/ent").uri("https://news.baidu.com/ent")).build();
        //配置 /sports 路由规则
        routes.route("path_route_atguigu_sports",r -> r.path("/sports").uri("https://news.baidu.com/sports")).build();
        //配置 /internet 路由规则
        routes.route("path_route_atguigu_internet",r -> r.path("/internet").uri("https://news.baidu.com/internet")).build();
        //配置 /tech 路由规则
        routes.route("path_route_atguigu_tech",r -> r.path("/tech").uri("https://news.baidu.com/tech")).build();
        //配置 /game 路由规则
        routes.route("path_route_atguigu_game",r -> r.path("/game").uri("https://news.baidu.com/game")).build();
        //配置 /lady 路由规则
        routes.route("path_route_atguigu_lady",r -> r.path("/lady").uri("https://news.baidu.com/lady")).build();
        //配置 /auto 路由规则
        routes.route("path_route_atguigu_auto",r -> r.path("/auto").uri("https://news.baidu.com/auto")).build();
        //配置 /house 路由规则
        routes.route("path_route_atguigu_house",r -> r.path("/house").uri("https://news.baidu.com/house")).build();
        //配置 / 路由规则（首页）
        routes.route("path_route_atguigu_house",r -> r.path("/").uri("https://news.baidu.com/")).build();

        return routes.build();
    }

}
