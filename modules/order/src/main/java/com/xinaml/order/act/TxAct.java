package com.xinaml.order.act;

import com.xinaml.common.hystrix.HystrixCommand;
import com.xinaml.order.ser.BusinessSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 上午11:40]
 * @Description: 事务测试
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class TxAct {

    @Autowired
    private BusinessSer businessSer;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @HystrixCommand
    @PostMapping("/purchase/commit")
    public Boolean purchaseCommit() {
        businessSer.purchase("1", "手机", 1, 100.0);
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     *
     * @return
     */
    @HystrixCommand
    @PostMapping("/purchase/rollback")
    public String purchaseRollback() {
        businessSer.purchase("2", "手机", 1, 100.0);
        return "success";
    }


}
