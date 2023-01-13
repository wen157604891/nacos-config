package com.wen.service2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wsw
 * @Date 2023/1/4 11:32
 **/
@RestController
@RequestMapping("/service2")
public class Service2Controller {

    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${com.name.service}")
    private String config;

    @GetMapping("/getConfig")
    public String getConfig() {
        return context.getEnvironment().getProperty("com.name.service");
    }
}
