package com.gientech.stms.application.controller.dto;
import com.sunny.boot.base.enums.ValidateGroupEnum;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.sunny.boot.base.enums.LogicDeleteEnum;

/**
* 订单表(Order)表对像请求参数
*
* @author sunzh
* @since 2025-01-02 17:48:47
*/
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单表对像请求参数")
public class OrderReqDTO  implements Serializable {
private static final long serialVersionUID = -25154920869172556L;
    /**
    * 主键
    */
    @ApiModelProperty("主键")
        @NotNull(groups =ValidateGroupEnum.UpdateGroup.class)
    @Min(value = 1,groups =ValidateGroupEnum.UpdateGroup.class)
    private Long id;
        /**
    * 创建人
    */
    @ApiModelProperty("创建人")
        private String createdBy;
        /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
        private LocalDateTime createdTime;
        /**
    * 是否删除  Y：已删除  N：正常
    */
    @ApiModelProperty("是否删除  Y：已删除  N：正常")
        private LogicDeleteEnum  delFlag;
        /**
    * 姓名
    */
    @NotEmpty(groups =ValidateGroupEnum.UpdateGroup.class)
    @ApiModelProperty("姓名")
        private String name;
        /**
    * 更新人
    */
    @ApiModelProperty("更新人")
        private String updatedBy;
        /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
        private LocalDateTime updatedTime;
    


}

