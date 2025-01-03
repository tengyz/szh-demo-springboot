package skdapp.cn.fulltext.util;

import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring-context上下文环境和EntityManager相关对象
 * 
 * @project common-utils
 * @fileName ApplicationUtil.java
 * @Description
 * @author light-zhang
 * @date 2019年4月29日
 * @version 1.0.0
 */
@Component
public class ApplicationUtil implements ApplicationContextAware { 
	/**
	 * 上下文环境
	 */
	@Autowired
	private static ApplicationContext applicationContext;
	/**
	 * jpa对象管理器
	 */
	private static EntityManager entityManager;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Autowired
	public ApplicationUtil(EntityManager entityManager) {
		ApplicationUtil.entityManager = entityManager;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 关闭实体管理
	 */
	public static void closeEntityManager() {
		if (!Objects.isNull(ApplicationUtil.getEntityManager())) {
			getEntityManager().close();
		}
	}
}
