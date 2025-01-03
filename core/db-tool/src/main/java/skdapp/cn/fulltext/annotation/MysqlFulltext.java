package skdapp.cn.fulltext.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 全文索引注解
 * 
 * @作者 light-zhang
 * @时间 2019年3月13日
 * @product order
 * @package cc.zeelan.mall.order.common
 * @file FruitProvider.java
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MysqlFulltext {
	/**
	 * 索引名称
	 * 
	 * @return
	 */
	String indexesName() default ""; 
	/**
	 * 对应的列
	 */
	String columnName();
}
