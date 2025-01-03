package com.sunny.boot.dbtool.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunny.boot.base.persistent.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@TableName(DemoDO.TABLE_NAME)
public class DemoDO extends BaseDO {
    public final static String TABLE_NAME="gj_demo";

    @TableField(value = "name")
    String name;
}
