package com.order.service;

import com.order.dao.OrderDao;
import com.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: OrderService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: H_jy
 * @date: 2019-04-02 15:50
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public List<Order> getOrderByPage(Order order) {
        return orderDao.getOrderByPage(order);
    }
}
