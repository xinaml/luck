package com.xinaml.order.ser;

import com.xinaml.order.entity.Order;
import com.xinaml.order.feign.StorageFeign;
import com.xinaml.order.feign.UserFeign;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 上午11:41]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class BusinessSer {

    @Autowired(required = false)
    private StorageFeign storageFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private OrderSer orderSer;
    /**
     * 减库存，下订单
     *
     * @param userId
     * @param name
     * @param count
     */

    @GlobalTransactional
    public void purchase(String userId, String name, int count,Double price) {
        String xid = RootContext.getXID();
        storageFeign.subtract(name, count);//减去库存
        userFeign.subtract(userId, price);//减去用户存款
        if(userId.equals("2")){
            throw new RuntimeException("下单失败！");
        }
        //生成订单
        Order order = new Order();
        order.setCreateDate(LocalDateTime.now());
        order.setPrice(price);
        order.setCount(count);
        order.setName(name);
        order.setUserId(userId);
        orderSer.save(order);

    }
}
