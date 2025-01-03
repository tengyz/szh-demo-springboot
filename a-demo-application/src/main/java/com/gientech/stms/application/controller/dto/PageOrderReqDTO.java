package com.gientech.stms.application.controller.dto;
 
import com.sunny.boot.base.controller.dto.PageReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 
/**
* 订单表分页请求参数
*
* @author sunzh
* @since 2025-01-02 19:48:19
*/ 
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单表分页请求参数")
public class PageOrderReqDTO extends PageReqDTO {
    /**
    * 查询条件
    */
    @ApiModelProperty("查询条件")
    OrderReqDTO queryParam;



}

