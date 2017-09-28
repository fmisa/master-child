package misa;

import misa.masterchild.dao.mybatis.example.OrderExample;
import misa.masterchild.dao.mybatis.mapper.OrderMapper;
import misa.masterchild.dao.mybatis.transaction.MyBatisTransaction;
import misa.masterchild.model.Order;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MyBatisTester {

    private static final String ORDER_CUSTOMER_NAME = "testUser";
    private static final String UPDATED_ORDER_CUSTOMER_NAME = ORDER_CUSTOMER_NAME + "UPDATED";

    @Test
    public void testCRUD() throws IOException {
        createOrder();
        lookupOrder();
        deleteAllOrders();
    }

    private void createOrder() throws IOException {
        MyBatisTransaction myBatisTransaction = new MyBatisTransaction();
        myBatisTransaction.process(myBatisTransaction.new MyBatisTransactionProcessor() {
            @Override
            public void process() throws IOException {
                Order testOrder = Order.newBuilder()
                        .withCustomername(ORDER_CUSTOMER_NAME)
                        .withStatus(false)
                        .build();

                OrderMapper orderMapper = openSQLSession().getMapper(OrderMapper.class);

                orderMapper.insert(testOrder);
            }
        });
    }

    private void lookupOrder() throws IOException {
        MyBatisTransaction myBatisTransaction = new MyBatisTransaction();
        myBatisTransaction.process(myBatisTransaction.new MyBatisTransactionProcessor() {
            @Override
            public void process() throws IOException {
                OrderExample orderExample = new OrderExample();
                orderExample.createCriteria().andCustomernameEqualTo(ORDER_CUSTOMER_NAME);

                OrderMapper orderMapper = openSQLSession().getMapper(OrderMapper.class);
                List<Order> orderList = orderMapper.selectByExample(orderExample);

                assertEquals("Expected order not found", 1, orderList.size());

                updateOrder(orderList.get(0));
            }
        });
    }

    private void updateOrder(final Order order) throws IOException {
        MyBatisTransaction myBatisTransaction = new MyBatisTransaction();
        myBatisTransaction.process(myBatisTransaction.new MyBatisTransactionProcessor() {
            @Override
            public void process() throws IOException {
                OrderMapper orderMapper = openSQLSession().getMapper(OrderMapper.class);

                //update the order
                OrderExample orderSearchCriteria = new OrderExample();
                orderSearchCriteria.createCriteria().andIdEqualTo(order.getId());
                order.setCustomername(UPDATED_ORDER_CUSTOMER_NAME);
                orderMapper.updateByExample(order, orderSearchCriteria);

                //lookup and verify saved correctly
                orderSearchCriteria.getOredCriteria().get(0).andCustomernameEqualTo(UPDATED_ORDER_CUSTOMER_NAME);
                List<Order> orderList = orderMapper.selectByExample(orderSearchCriteria);
                assertEquals("Expected order not found", 1, orderList.size());

                Order updatedOrder = orderList.get(0);
                assertEquals("Expected updated not detected", UPDATED_ORDER_CUSTOMER_NAME, updatedOrder.getCustomername());
            }
        });
    }

    private void deleteAllOrders() throws IOException {
        MyBatisTransaction myBatisTransaction = new MyBatisTransaction();
        myBatisTransaction.process(myBatisTransaction.new MyBatisTransactionProcessor() {
            @Override
            public void process() throws IOException {
                OrderMapper orderMapper = openSQLSession().getMapper(OrderMapper.class);
                //Fetch all orders
                OrderExample orderExample = new OrderExample();
                List<Order> orderList = orderMapper.selectByExample(orderExample);
                assertTrue("Should have stored Orders", orderList.size() > 0);

                //Remove all orders
                for (Order order : orderList) {
                    orderExample.createCriteria().andIdEqualTo(order.getId());
                    orderMapper.deleteByExample(orderExample);
                }
                orderExample = new OrderExample();
                orderList = orderMapper.selectByExample(orderExample);
                assertTrue("Should have stored Orders", orderList.size() == 0);
            }
        });
    }
}
