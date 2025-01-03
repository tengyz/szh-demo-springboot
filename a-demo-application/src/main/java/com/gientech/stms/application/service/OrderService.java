package com.gientech.stms.application.service;

import com.gientech.stms.application.persistent.entity.OrderDO; 

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.List;
/**
 * 订单表(Order)表服务接口
 *
 * @author sunzh
 * @since 2025-01-02 17:48:47
 */

public interface OrderService {
 /**
     * 分页查询
     *
     * @param param 筛选条件
     * @param pageSize      分页大小
     * @param pageNum      起始页
     * @return 查询结果
     */
    IPage<OrderDO> page(OrderDO param, Integer pageSize, Integer pageNum);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderDO load(Long  id);

    /**
     * 修改数据
     *
     * @param param 实例对象
     * @return 实例对象
     */
    void update(OrderDO param);
     /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    void remove(Long id);
     /**
     * 根据条件查询数据
     *
     * @param param 查询条件
     * @return 是否成功
     */
    List<OrderDO> list(OrderDO param);
    /**
     * 新增数据
     *
     * @param param 实例对象
     * @return 实例对象
     */
     Serializable add(OrderDO param);
}

 
