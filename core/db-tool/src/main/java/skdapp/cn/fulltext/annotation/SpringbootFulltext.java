package skdapp.cn.fulltext.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import skdapp.cn.fulltext.constant.Constant;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScans({ @ComponentScan("skdapp.cn.fulltext") })
public @interface SpringbootFulltext {
	/**
	 * 全文索引扫描的路径
	 */
	String[] scanPackages();

	/**
	 * 数据库类型
	 * 
	 * @return
	 */
	String dbType() default Constant.MYSQL;

}
