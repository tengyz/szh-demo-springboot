package com.gientech.stms.application.controller;

import com.gientech.stms.application.persistent.entity.OrderDO;
import com.gientech.stms.application.service.OrderService;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sunny.boot.base.controller.dto.PageResponseDTO;
import com.sunny.boot.base.controller.dto.ResponseDTO;
import com.gientech.stms.application.controller.dto.OrderReqDTO;
import com.gientech.stms.application.controller.dto.OrderRspDTO;
import com.gientech.stms.application.controller.dto.PageOrderReqDTO; 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import com.sunny.boot.base.exception.code.ResultEnum;
import com.sunny.boot.base.enums.ValidateGroupEnum;
import java.io.Serializable;
/**
 * 订单表(Order)表控制层
 *
 * @author sunzh
 * @since 2025-01-02 19:43:19
 */
@RestController
@RequestMapping("/gj/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "订单表模块接口")
public class OrderController { 
     /**
     * 服务对象
     */ 
    private final  OrderService orderService;
     /**
     * 分页查询
     * 
     * @param req      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询列表")
    @PostMapping("/page")
    public PageResponseDTO<OrderRspDTO> page(@Validated({ValidateGroupEnum.PageGroup.class})  @RequestBody PageOrderReqDTO req) {
 
        IPage<OrderDO> result=this.orderService.page(BeanUtil.copyProperties(req.getQueryParam(), OrderDO.class)
                ,req.getPageSize(),req.getPageNum());
        return PageResponseDTO.<OrderRspDTO>builder().
                count(result.getTotal()).data(
                        result.getRecords().stream().map(v->OrderRspDTO.builder()
                                        //TODO 控制层进行对像转换
                                        .name(v.getName())
                                        .build())
                                .collect(Collectors.toList())
                ).code(ResultEnum.SUCCESS.getCode()).desc(ResultEnum.SUCCESS.getMessage()).build();
    }
  
 
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据主键加载数据")
    @GetMapping("/load/{id}")
    public ResponseDTO<OrderRspDTO> load(@PathVariable(value = "id") Long id){
        OrderDO OrderDO=  this.orderService.load(id);

        return ResponseDTO.succeed(OrderRspDTO.builder()
                //TODO 控制层进行对像转换
                .name(OrderDO.getName())
                .build());

    }

    @ApiOperation(value = "更新数据")
    @PostMapping("/update")
    public ResponseDTO update(@Validated({ValidateGroupEnum.UpdateGroup.class}) @RequestBody OrderReqDTO req){

        this.orderService.update(BeanUtil.copyProperties(req, OrderDO.class));
        return ResponseDTO.succeed();
    }
    @ApiOperation(value = "逻辑删除数据")
    @GetMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable(value = "id") Long id){
        this.orderService.remove(id);
        return ResponseDTO.succeed();
    }
    @ApiOperation(value = "根据条件查询数据")
    @PostMapping("/list")
    public ResponseDTO<List<OrderRspDTO>> list(@Validated({ValidateGroupEnum.ListGroup.class}) @RequestBody OrderReqDTO req){

        List<OrderDO> result= this.orderService.list(BeanUtil.copyProperties(req, OrderDO.class));
        return ResponseDTO.succeed( result.stream().map(v->OrderRspDTO.builder()
                        //TODO 控制层进行对像转换
                        .name(v.getName())
                        .build())
                .collect(Collectors.toList()));
    }
    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public ResponseDTO add(@Validated({ValidateGroupEnum.AddGroup.class}) @RequestBody OrderReqDTO req){

        Serializable id=this.orderService.add(BeanUtil.copyProperties(req, OrderDO.class));
        return ResponseDTO.succeed(id);
    }
}

