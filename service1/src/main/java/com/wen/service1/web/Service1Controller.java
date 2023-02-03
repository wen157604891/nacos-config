package com.wen.service1.web;

import com.wen.base.web.MyControllerSupport;
import com.wen.service1.fileUtil.ConfigFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wsw
 * @Date 2023/1/4 11:32
 **/
@RestController
@RequestMapping("/service1")
//动态刷新config配置注解
@RefreshScope
public class Service1Controller extends MyControllerSupport {

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
    @Value("${file.path}")
    private String filePath;

    @GetMapping("/getConfig")
    public String getConfig() {
        return config;
    }


    @GetMapping("/getExtConfig")
    public String getExtConfig() {
        return name + "  " + address + " " + age + " " + fullName;
    }


    @GetMapping("/getPath")
    public String getPath() {
        return filePath;
    }

    @GetMapping("/getProValue")
    public String getProValue() {
        return ConfigFileUtils.getPropertiesValue("param.properties", "test");
    }


    @GetMapping("/getPage")
    public Page<Map<String, Object>> getPage() {
        List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "张三");
        map1.put("age", 18);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "李四");
        map2.put("age", 16);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "王五");
        map3.put("age", 17);
        pageList.add(map1);
        pageList.add(map2);
        pageList.add(map3);
        Page<Map<String, Object>> mapPage = new PageImpl<Map<String, Object>>(pageList, PageRequest.of(1, 2), 3);
        return mapPage;
    }


}
