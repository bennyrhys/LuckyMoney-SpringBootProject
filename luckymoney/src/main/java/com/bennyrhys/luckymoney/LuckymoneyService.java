package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Service
public class LuckymoneyService {
    @Autowired
    private LuckymoneyRepository luckymoneyRepository;
    /**
     * 创建两个红包
     */
    @Transactional
    public void createTwo(){
        Luckmoney luckmoney = new Luckmoney();
        luckmoney.setProducer("瑞新");
        luckmoney.setMoney(new BigDecimal("520"));

        luckymoneyRepository.save(luckmoney);
        Luckmoney luckmoney2 = new Luckmoney();
        luckmoney2.setProducer("瑞新");
        luckmoney2.setMoney(new BigDecimal("1314"));

        luckymoneyRepository.save(luckmoney2);
    }
}
