package com.gientech.stms.application.persistent.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunny.boot.base.persistent.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
/**
* gj_order(Order)表对像
*
* @author sunzh
* @since 2025-01-02 17:40:03
*/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@TableName(OrderDO.TABLE_NAME)
public class OrderDO extends BaseDO {
    public final static String TABLE_NAME="gj_order";

    @TableField(value = "name")
    String name;
}



