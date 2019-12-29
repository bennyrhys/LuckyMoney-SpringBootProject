package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloController {
//法1：手动引入配置信息
    @Value("${mixMoney}")
    private BigDecimal mixMoney;
    @Value("${description}")
    private String description;


    @GetMapping("/hello")
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public  String hello(){
        return "mixMoney"+mixMoney+"说明"+description;
    }
}
