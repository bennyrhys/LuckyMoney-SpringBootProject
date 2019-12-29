package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckmoneyController {

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;
    @Autowired
    private LuckymoneyService service;
    /**
     * 获取红包列表
     */
    @GetMapping("/luckymoneys")
    public List<Luckmoney> list(){
        return luckymoneyRepository.findAll();
    }
    /**
     * 创建红包(发红包)
     * 1.写入报错，因为jpa主键自增，之前手动添加数据删除就行
     * Hibernate: select next_val as id_val from hibernate_sequence for update
     * Hibernate: update hibernate_sequence set next_val= ? where next_val=?
     * Hibernate: insert into luckmoney (consumer, money, producer, id) values (?, ?, ?, ?)
     * SQL Error: 1062, SQLState: 23000
     * Duplicate entry '1' for key 'PRIMARY'
     * 2.乱码解决方案
     * yml配置url后加？characterEncoding=utf-8
     *
     */
    @PostMapping("/luckymoneys")
    public Luckmoney create(@RequestParam("producer") String producer,
                            @RequestParam("money")BigDecimal money ){
        Luckmoney luckmoney = new Luckmoney();
        luckmoney.setProducer(producer);
        luckmoney.setMoney(money);

        return luckymoneyRepository.save(luckmoney);
    }
    /**
     * 通过id查询红包
     * http://localhost:8081/luckymoney/luckymoneys/2
     * {
     *     "id": 2,
     *     "money": 100.00,
     *     "producer": "瑞新",
     *     "consumer": null
     * }
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckmoney findById(@PathVariable("id") Integer id){
        //查到了返回对象，查不到返回null
        return luckymoneyRepository.findById(id).orElse(null);
    }

    /**
     * 更新红包（领红包）
     */
    @PutMapping("/luckymoneys/{id}")
    public Luckmoney update(@PathVariable("id") Integer id,
                            @RequestParam("consumer")String consumer){
        
        //先查询数据，未查询的数据为null
        Optional<Luckmoney> optional = luckymoneyRepository.findById(id);
        //如果有内容才更新
        if (optional.isPresent()){
            Luckmoney luckmoney = optional.get();
            //更新数据
            luckmoney.setConsumer(consumer);

            return luckymoneyRepository.save(luckmoney);

        }
        return null;

    }

    /**
     * 发两个红包-测试
     */
    @PostMapping("luckymoneys/two")
    public void creatTwo(){
        service.createTwo();
    }
}
