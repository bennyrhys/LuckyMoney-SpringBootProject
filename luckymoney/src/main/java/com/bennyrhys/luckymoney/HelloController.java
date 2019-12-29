package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@RequestMapping("hello")
@RestController
public class HelloController {

    @Autowired
    LimitConfig limitConfig;

    @PostMapping("/say")
    public  String hello(@RequestParam(value = "id" , required = false, defaultValue = "0") Integer id){
        return "id:"+id;
    }

}
