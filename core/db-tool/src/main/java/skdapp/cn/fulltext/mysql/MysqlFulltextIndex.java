package skdapp.cn.fulltext.mysql;

import java.lang.reflect.Array;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.util.StringUtils;

import skdapp.cn.fulltext.constant.Constant;
import skdapp.cn.fulltext.util.ApplicationUtil;
import skdapp.cn.fulltext.util.Assert;

@Configuration
public class MysqlFulltextIndex {
	/**
	 * 解析字符串
	 * 
	 * @param str
	 * @param start
	 * @return
	 */
	private String substring(String str, int start) {
		return org.apache.commons.lang3.StringUtils.substring(str, start);
	}

	@Value("${spring.datasource.url}")
	private String datasouceUrl;

	/**
	 * 查询所有的索引,修复支持mysql8.0版本
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllFulltexts() {
		String schema = getSchema(datasouceUrl);
		StringBuffer sql = new StringBuffer("SELECT INDEX_NAME");
		sql.append(" FROM INFORMATION_SCHEMA.STATISTICS");
		sql.append(" WHERE TABLE_SCHEMA ='".concat(schema).concat("'"));
		sql.append(" AND INDEX_TYPE='FULLTEXT' ");
		Query query = ApplicationUtil.getEntityManager().createNativeQuery(sql.toString());
		return query.getResultList();
	}

	/**
	 * 创建全文索引
	 */
	public void createIndex(String tableName, String indexName, String columnName) {
		final StringBuffer createIndex = new StringBuffer("CREATE FULLTEXT INDEX ");
		createIndex.append(indexName);
		createIndex.append(" ON ");
		createIndex.append(tableName);
		createIndex.append("(");
		createIndex.append(columnName);
		createIndex.append(")");
		createIndex.append(" WITH PARSER ngram");// 采用MYSQ Lngram全文解析器
		excute(createIndex.toString());// 执行创建全文索引操作
		createIndex.setLength(0);// 清空
	}

	/**
	 * 获取数据库名称
	 * 
	 * @param url
	 * @return
	 */
	public String getSchema(String url) {
		Assert.IllegalArgumentException(StringUtils.isEmpty(url), Constant.DB_URL_ERROR);
		String schemaName = null;
		if (url.indexOf("?") < 0) {// 不包含
			schemaName = this.substring(url, url.lastIndexOf("/") + 1);
		} else {
			String temp[] = StringUtils.split(url, "?");
			if (Array.getLength(temp) >= 0) {
				String tempSchema = this.substring(url, temp[0].lastIndexOf("/") + 1);
				String schema[] = StringUtils.split(tempSchema, "?");
				if (Array.getLength(schema) >= 0) {
					schemaName = schema[0];
				}
			}
		}
		return schemaName;
	}

	/**
	 * 删除索引
	 */
	public void deleteIndex(String tableName, String indexName) {
		final StringBuffer deleteIndex = new StringBuffer("ALTER TABLE ");
		deleteIndex.append(tableName);
		deleteIndex.append(" DROP INDEX ");
		deleteIndex.append(indexName);
		excute(deleteIndex.toString());// 执行删除索引操作
		deleteIndex.setLength(0);// 清空
	}

	/**
	 * CURD执行器
	 * 
	 * @param sql
	 * @return
	 */
	@Modifying(clearAutomatically = true)
	private int excute(String sql) {
		Query query = ApplicationUtil.getEntityManager().createNativeQuery(sql);
		return query.executeUpdate();
	}
}
