package com.wondersgroup.empi.util.anotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 数据库字段名
 * 
 * 用于转换数据字段,映射关系 数据库字段名与java类名
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Column {

	String name();//数据库字段名
	
}
