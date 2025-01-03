/**
* 订单表(Order)表Mapper定义
*
* @author sunzh
* @since 2025-01-02 17:48:47
*/


package com.gientech.stms.application.persistent.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gientech.stms.application.persistent.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

}

