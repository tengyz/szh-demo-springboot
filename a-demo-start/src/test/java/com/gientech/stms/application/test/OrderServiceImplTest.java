package com.gientech.stms.application.test;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gientech.stms.demo.start.StartDemoApplication;
import com.gientech.stms.application.persistent.entity.OrderDO;
import com.gientech.stms.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
/**
 * 订单表(Order)测试类
 *
 * @author sunzh
 * @since 2025-01-02 18:03:33
 */

@SpringBootTest(classes = StartDemoApplication.class)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag("junit_order")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceImplTest {
    /**
    * 服务对象
    */
    private final  OrderService orderService;


    private final static  String DATA_NAME = String.format("测试%s", new Date().getTime());
    private static Long id = null;

    @Test
    @Tag("junit_test")
    void test(){

    }
    @Order(2)
    @Test
    void page() {
        IPage<OrderDO> page = this.orderService.page(OrderDO.builder().name(DATA_NAME).build(), 10, 2);
        Assertions.assertEquals(15L, page.getTotal());
        Assertions.assertEquals(5L, page.getRecords().size());
    }

    @Order(4)
    @Test
    void load() {
         OrderDO orderDO = this.orderService.load(id);
        Assertions.assertEquals("测试", orderDO.getName());
    }

    @Order(3)
    @Test
    void update() {
        this.orderService.update(OrderDO.builder().id(id).name("测试").build());
    }

    @Order(5)
    @Test
    void remove() {
        this.orderService.remove(id);
    }

    @Order(6)
    @Test
    void list() {
        List<OrderDO> list = this.orderService.list(OrderDO.builder().name(DATA_NAME).build());
        Assertions.assertEquals(14L, list.size());
    }
    @RepeatedTest(15)
    @Order(1)
    void add() {
        id = (Long) this.orderService.add(OrderDO.builder().name(DATA_NAME).build());
        Assertions.assertNotNull(id);
    }
}

