package com.gientech.stms.application.persistent.jpa;



import com.gientech.stms.application.persistent.entity.OrderDO;
import org.hibernate.annotations.Comment;
import skdapp.cn.fulltext.config.TableComment;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
* gj_order(Order)表JPA定义
*
* @author sunzh
* @since 2025-01-02 17:41:36
*/
@Entity(name = OrderDO.TABLE_NAME)
@TableComment("订单表")
@Table(name = OrderDO.TABLE_NAME, indexes = {
@Index(columnList = "delFlag") }
)
public class OrderJPA extends OrderDO {
    @Override
    @Id
    @Comment("主键")
    public Long getId() {
        return super.getId();
    }

    @Override
    @Comment("姓名")
    public String getName() {
        return super.getName();
    }

    @Comment("创建人")
    @Column( length = 64)
    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @Comment("更新人")
    @Override
    @Column( length = 64)
    public String getUpdatedBy() {
        return super.getUpdatedBy();
    }

    @Comment("创建时间")
    @Override
    public LocalDateTime getCreatedTime() {
        return super.getCreatedTime();
    }

    @Comment("更新时间")
    @Override
    public LocalDateTime getUpdatedTime() {
        return super.getUpdatedTime();
    }
    @Comment("是否删除  Y：已删除  N：正常")
    @Column( columnDefinition = " varchar(1) NOT NULL DEFAULT 'N' " )
    @Override
    public String getDelFlag() {
        return super.getDelFlag();
    }
}



