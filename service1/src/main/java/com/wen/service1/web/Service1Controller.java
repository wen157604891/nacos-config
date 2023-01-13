package com.wen.service1.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wsw
 * @Date 2023/1/4 11:32
 **/
@RestController
@RequestMapping("/service1")
//动态刷新config配置注解
@RefreshScope
public class Service1Controller {

    @Value("${com.name.service}")
    private String config;
    @Value("${common.name}")
    private String name;
    @Value("${common.address}")
    private String address;
    @Value("${common.age}")
    private String age;
    @Value("${common.fullName}")
    private String fullName;

    @GetMapping("/getConfig")
    public String getConfig(){
        return config;
    }


    @GetMapping("/getExtConfig")
    public String getExtConfig(){
        return name+"  "+address+" "+age+" "+ fullName;
    }
}
