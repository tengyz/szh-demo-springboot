package com.sunny.boot.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.sunny.boot.base"})
@MapperScan("com.**.mapper.**")
public class AutoConfiguration {
}
