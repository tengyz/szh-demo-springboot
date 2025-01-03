package com.gientech.stms.application.service.impl;
import org.apache.commons.lang3.StringUtils;
import com.gientech.stms.application.persistent.entity.OrderDO;
import com.gientech.stms.application.persistent.dao.OrderDAO;
import com.gientech.stms.application.service.OrderService;
import com.gientech.stms.application.exception.OrderCodeExceptionEnum;
import com.sunny.boot.base.exception.code.DbCodeBusinessExceptionEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import com.sunny.boot.base.exception.BusinessException;
import java.util.Optional;
/**
 * 订单表(Order)表服务实现
 *
 * @author sunzh
 * @since 2025-01-02 17:55:09
 */

@Validated
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
 /**
     * 分页查询
     *
     * @param param 筛选条件
     * @param pageSize      分页大小
     * @param pageNum      起始页
     * @return 查询结果
     */
    @Override
    public IPage<OrderDO> page(OrderDO param, Integer pageSize, Integer pageNum){
        return this.orderDAO.page(param, pageSize, pageNum, (queryParam, wrapper) -> {
            //TODO 完成分页查询逻辑
            wrapper.like(StringUtils.isNotBlank(queryParam.getName()),OrderDO::getName,queryParam.getName());
        });
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderDO load(Long  id){
        return this.orderDAO.load(id);
    }

    /**
     * 修改数据
     *
     * @param param 实例对象
     * @return 实例对象
     */
    @Override
    public void update(OrderDO param){
        OrderDO orderDO= this.orderDAO.update(param.getId(), param, (queryParam, dbData) -> {
            //TODO 将页面的值赋值到数据库对像中
            dbData.setName(queryParam.getName());
        });
        if(!Optional.ofNullable(orderDO).isPresent()){
            throw new BusinessException(DbCodeBusinessExceptionEnum.更新失败);
        }
    }
     /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void remove(Long id) {
        Boolean flag= this.orderDAO.remove(id);
        if(!Optional.ofNullable(flag).isPresent()){
            throw new BusinessException(DbCodeBusinessExceptionEnum.删除失败);
        }
    }
     /**
     * 根据条件查询数据
     *
     * @param param 查询条件
     * @return 是否成功
     */
    @Override
    public List<OrderDO> list(OrderDO param) {
        return this.orderDAO.list(param, (queryParam, wrapper) -> {
            //TODO 完成分页查询逻辑
            wrapper.like(StringUtils.isNotBlank(queryParam.getName()),OrderDO::getName,queryParam.getName());
        });

    }
    /**
     * 新增数据
     *
     * @param param 实例对象
     * @return 实例对象
     */
    @Override
    public Serializable add(OrderDO param) {
        Serializable id= this.orderDAO.add(param.getId(), param, (queryParam,dbData) -> {
            //TODO 根据前端传的值构造数据库对像
            if(StringUtils.isNotBlank(queryParam.getName())){
                dbData.setName(queryParam.getName());
            }
        });
        if(!Optional.ofNullable(id).isPresent()){
            throw new BusinessException(DbCodeBusinessExceptionEnum.新增失败);
        }
        return id;
   }
}


