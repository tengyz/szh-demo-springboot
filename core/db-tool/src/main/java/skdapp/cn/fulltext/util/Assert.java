package skdapp.cn.fulltext.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import skdapp.cn.fulltext.constant.Constant;

/**
 * 常用的异常信息
 * 
 * @project common-utils
 * @fileName Assert.java
 * @Description
 * @author light-zhang
 * @date 2019年4月17日
 * @version 1.0.0
 */
public abstract class Assert extends org.springframework.util.Assert {
	/**
	 * 常见异常封装处
	 * 
	 * @param message
	 * @param cause
	 */
	public static void serviceException(@Nullable String message, Throwable cause) {
		try {
			throw new Exception(message, cause);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 运行时异常
	 * 
	 * @param message
	 * @param cause
	 */
	public static void RuntimeErrorException(@Nullable String message, Throwable cause) {
		throw new RuntimeException(message, cause);
	}

	/**
	 * 参数错误异常
	 * 
	 * @param message 参数错误异常信息
	 */
	public static void IllegalArgumentException(@Nullable boolean expression, @Nullable String message) {
		if (expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 空指针异常
	 * 
	 * @param message 空指针异常信息
	 */
	public static void NullPointerException(@Nullable Object object, String message) {
		if (StringUtils.isEmpty(object)) {
			throw new NullPointerException(message);
		}
	}

	public static void arrayNotNull(String[] params) {
		Assert.IllegalArgumentException(params == null || params.length <= 0, Constant.CREATE_ERROR);
	}
}
