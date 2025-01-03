```
 springboot支持mysql5.7版本以上根据注解创建全文索引
使用方法:
 
 2.项目中引入依赖  
 <dependency>
	<groupId>skdapp.cn</groupId>
	<artifactId>springboot.fulltext</artifactId>
	<version>0.0.1</version> 
  </dependency>     
3.在项目中使用  
//启动类注入(scanPackages你的om扫描路径)
@SpringbootFulltext(scanPackages = { "skdapp.cn.xxx.entity" })
@SpringBootApplication(scanBasePackages = { "skdapp.cn.xxx.xxx" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
} 

4.实体类使用 
@Entity
@Table(name = "demo")
public class Demo implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "pk_id", nullable = false, unique = true, length = 32, columnDefinition = "varchar(32) COMMENT '主键ID，生成32位随机字符串' ")
    private String pkId;

    @MysqlFulltext(columnName = "content")
    @Column(name = "content", columnDefinition = "text COMMENT '内容' ")
    private String content;
} 

启动项目时,就可以根据Fulltext注解的配置自动扫描创建全文索引了 
@MysqlFulltext默认生成全文索引策略table名称_column列名_idx,
	如果@MysqlFulltext使用了属性indexesName则按照indexesName的名称为全文索引命名

```
