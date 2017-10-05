package misa.masterchild.service;

import misa.masterchild.dao.mybatis.mapper.OrderMapper;
import misa.masterchild.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderMapper orderDetailOrderMapper;

    public int saveOrder(Order order) {
        return orderMapper.insert(order);
    }

}
