package com.sunny.boot.base.persistent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunny.boot.base.enums.TradeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

/**
 * 附件
 * @author
 * @date
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@TableName(AttachmentDO.TABLE_NAME)
public class AttachmentDO  extends BaseDO {
    public final static String TABLE_NAME="gj_remit_attachment";
    /**
     * 文件名
     */
    @TableField(value = "name")
    String name;
    /**
     * 文件后缀
     */
    @TableField(value = "suffix")
    String suffix;
    /**
     * 文件大小
     */
    @TableField(value = "size")
    Long size;
    /**
     * 文件存储路径
     */
    @TableField(value = "relation_path")
    String relationPath;
    /**
     * 文件映射名
     */
    @TableField(value = "mapping_name")
    String mappingName;
    /**
     * 交易类型
     */
    @TableField(value = "trade_type")
    TradeTypeEnum tradeType;
    /**
     * 外键表Id
     */
    @TableField(value = "reference_id")
    Long referenceId;

}
