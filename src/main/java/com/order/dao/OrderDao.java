package com.order.dao;

import com.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: OrderDao
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: H_jy
 * @date: 2019-04-02 14:20
 */
@Service
public interface OrderDao {
        List<Order> getOrderByPage(Order order);
}
