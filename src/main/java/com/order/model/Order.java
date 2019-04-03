package com.order.model;

import org.springframework.stereotype.Service;

/**
 * @ClassName: Order
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: H_jy
 * @date: 2019-04-02 14:20
 */
@Service
public class Order extends Common{
    private int o_id;
    private String o_phone;

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getO_phone() {
        return o_phone;
    }

    public void setO_phone(String o_phone) {
        this.o_phone = o_phone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_id=" + o_id +
                ", o_phone='" + o_phone + '\'' +
                '}';
    }
}
