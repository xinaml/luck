package com.xinaml.order.ser;

import com.xinaml.jpa.ser.ServiceImpl;
import com.xinaml.order.dto.OrderDTO;
import com.xinaml.order.entity.Order;
import org.springframework.stereotype.Service;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 上午11:54]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class OrderSerImpl extends ServiceImpl<Order, OrderDTO> implements OrderSer {
}
