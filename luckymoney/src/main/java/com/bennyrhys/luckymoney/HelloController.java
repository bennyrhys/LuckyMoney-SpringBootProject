package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
//@ResponseBody
public class HelloController {

//法2；自动注入
    @Autowired
    LimitConfig limitConfig;

    @ResponseBody
    @GetMapping("/hello")
    public  String hello(){
        return "说明"+limitConfig.getDescription();
//        return "index";
    }
    @GetMapping("/hello2")
    public  String hello2(){
        return "index";
    }
}
