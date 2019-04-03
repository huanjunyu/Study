package com.order.controller;

import com.order.model.Common;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: Order
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: H_jy
 * @date: 2019-04-02 15:49
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("getOrderByPage")
    public Object getOrderByPage(){
        Order order=new Order();
        order.setPagebegin(1);
        order.setPagesize(2);
        orderService.getOrderByPage(order);
        return order;
    }

}
