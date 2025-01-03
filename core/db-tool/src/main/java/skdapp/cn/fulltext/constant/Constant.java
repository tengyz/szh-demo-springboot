package skdapp.cn.fulltext.constant;

/**
 * 定义常量
 * 
 * @author light-zhang
 *
 */
public interface Constant {
	static final String MYSQL = "mysql";
	static final String ORACLE = "oracle";

	static final String CREATE_ERROR = "创建全文索引错误!请在@SpringbootFulltext配置扫描路径";
	static final String DBTYPE_ERROR = "数据库不支持!";
	static final String DOSCAN_ERROR = "扫描包类信息错误";
	static final String TABLE_ERROR = "@table(name='') specified for entity";
	static final String DB_URL_ERROR="create mysql fulltext error!checked db url";
}
