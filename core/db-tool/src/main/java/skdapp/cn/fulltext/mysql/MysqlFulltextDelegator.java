package skdapp.cn.fulltext.mysql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import skdapp.cn.fulltext.annotation.MysqlFulltext;
import skdapp.cn.fulltext.annotation.SpringbootFulltext;
import skdapp.cn.fulltext.constant.Constant;
import skdapp.cn.fulltext.scan.ScanSupport;
import skdapp.cn.fulltext.util.ApplicationUtil;
import skdapp.cn.fulltext.util.Assert;

/**
 * 全文索引注解实现
 * 
 * @作者 light-zhang
 * @时间 2019年3月13日
 * @product order
 * @package cc.zeelan.mall.order.common.util
 * @file FulltextKeyImpl.java
 * @DLL
 */
@Configuration
@Transactional
public class MysqlFulltextDelegator implements CommandLineRunner {
	@Autowired
	private MysqlFulltextIndex mysqlFulltextIndex;

	@Override
	public void run(String... args) throws Exception {
		Map<String, Object> beans = ApplicationUtil.getApplicationContext()
				.getBeansWithAnnotation(SpringBootApplication.class);
		for (Map.Entry<String, Object> entry : beans.entrySet()) {
			SpringbootFulltext springbootFulltext = entry.getValue().getClass().getAnnotation(SpringbootFulltext.class);
			String scanPackages[] = springbootFulltext.scanPackages();
			Assert.arrayNotNull(scanPackages);
			Set<Class<?>> classes = ScanSupport.classInfos(scanPackages);
			switch (springbootFulltext.dbType().toLowerCase()) {
			case Constant.MYSQL:
				this.getAnnotation(classes);
				break;
			case Constant.ORACLE:

				break;
			default:
				Assert.IllegalArgumentException(true, Constant.DBTYPE_ERROR);
				break;
			}
		}
	}

	/**
	 * 处理全文注解相关
	 * 
	 * @param clazz
	 */
	public void getAnnotation(Set<Class<?>> clazzs) {
		if (!CollectionUtils.isEmpty(clazzs)) {
			List<String> fulltexts = mysqlFulltextIndex.getAllFulltexts();
			for (Class<?> fclass : clazzs) {
				Entity entity = fclass.getAnnotation(Entity.class);
				if (!Objects.isNull(entity)) {
					Table table = fclass.getAnnotation(Table.class);
					if (Objects.isNull(table) || StringUtils.isEmpty(table.name())) {
						Assert.notNull(table, Constant.TABLE_ERROR);
					}
					Method[] methods = fclass.getDeclaredMethods();
					for (Method method : methods) {

						MysqlFulltext fulltextKey = method.getAnnotation(MysqlFulltext.class);
						if (!Objects.isNull(fulltextKey)) {
                            createIndex(table,fulltexts,fulltextKey);

						}
					}
					Field[] fields = fclass.getDeclaredFields();
					for (Field field : fields) {

						MysqlFulltext fulltextKey = field.getAnnotation(MysqlFulltext.class);
						if (!Objects.isNull(fulltextKey)) {
							createIndex(table,fulltexts,fulltextKey);

						}
					}
				}
			}
		}
		ApplicationUtil.closeEntityManager();
	}

    private void createIndex(Table table, List<String> fulltexts, MysqlFulltext fulltextKey) {

        String indexName = null;
        indexName = table.name().concat("_").concat(fulltextKey.columnName()).concat("_idx");
        if (!StringUtils.isEmpty(fulltextKey.indexesName())) {
            indexName = fulltextKey.indexesName();
        }
        if (fulltexts.indexOf(indexName) < 0) {
            mysqlFulltextIndex.createIndex(table.name(), indexName, fulltextKey.columnName());
        }
    }
}
