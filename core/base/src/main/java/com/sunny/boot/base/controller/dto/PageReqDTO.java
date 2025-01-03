package com.sunny.boot.base.controller.dto;

import com.sunny.boot.base.enums.ValidateGroupEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReqDTO implements Serializable {
    @Min(value = 1, message = "每页条数不得小于1",groups = ValidateGroupEnum.PageGroup.class)
    @ApiModelProperty("每页条数")
    @Builder.Default
    private Integer pageSize = 50;

    @Min(value = 1, message = "页码不得小于1",groups = ValidateGroupEnum.PageGroup.class)
    @ApiModelProperty("页码")
    @Builder.Default
    private Integer pageNum = 1;
}
