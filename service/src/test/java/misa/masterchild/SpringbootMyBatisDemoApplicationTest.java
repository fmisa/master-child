package misa.masterchild;

import misa.masterchild.model.Order;
import misa.masterchild.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootMyBatisDemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SpringbootMyBatisDemoApplicationTest {

    @Autowired
    OrderService orderService;

    @Test
    public void testMain() throws Exception {
        Order testOrder = Order.newBuilder()
                .withCustomername("thisIsATestCustomerName")
                .withStatus(false)
                .build();

        orderService.saveOrder(testOrder);
    }
}