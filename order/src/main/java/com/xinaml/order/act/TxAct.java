package com.xinaml.order.act;

import com.xinaml.order.ser.BusinessSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 上午11:40]
 * @Description:
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
    @RequestMapping("/purchase/commit")
    public Boolean purchaseCommit() {
        businessSer.purchase("1", "手机", 1,100.0);
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     *
     * @return
     */
    @RequestMapping("/purchase/rollback")
    public Boolean purchaseRollback() {
        try {
            businessSer.purchase("2", "手机", 1,100.0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
