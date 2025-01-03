package com.gientech.stms.application.persistent.dao;


import com.sunny.boot.base.persistent.dao.BaseDAOImpl;
import com.sunny.boot.base.service.PermitsService;
import com.gientech.stms.application.persistent.entity.OrderDO;
import com.gientech.stms.application.persistent.mapper.OrderMapper;
import org.springframework.stereotype.Component;

/**
* 订单表(Order)表DAO层
*
* @author sunzh
* @since 2025-01-02 17:48:47
*/
@Component
public class OrderDAO extends BaseDAOImpl<OrderMapper, OrderDO> {

    protected OrderDAO(PermitsService permitsService) {
        super(permitsService);
    }
}

