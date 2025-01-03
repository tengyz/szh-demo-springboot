# szh-demo-springboot

# szh快速开发框架

### 工程介绍

代码自动生成，快速开发脚手架:

* core 核心包模块，通过jpa自动生成数据库表。
* a-demo-start 启动工程、工程配置文件、单元测试，把业务和核心工程jar依赖
* a-demo-application 业务模块工程，按照模块进行划分，模块之间不能相互调用，各自模块都是独立的


### 部署步骤

1.git pull code
2.设置maven的setting文件，加入阿里的仓库,maven编译工程
````
<mirror>
<id>aliyun-maven</id>
<mirrorOf>central</mirrorOf>
<name>aliyun maven</name>
<url>https://maven.aliyun.com/repository/public</url>
</mirror>
````

3.修改数据库application.properties配置文件，启动a-demo-start工程下的StartDemoApplicationTests类服务生产表，然后StartDemoApplication启动，访问接口文档
http://localhost:38080/doc.html
该test下可以进行单元测试，增删查改业务场景的测试。

4.安装代码生成插件EasyCode，安装好之后，在core工程resources下导入EasyCodeConfig.json，需要配合Database插件一起使用，在弹出框进行勾选-选择代码生成。
对应到a-demo-application 业务模块工程目录中可以生产代码有：
controller、service、serviceimpl、validate、dao、do、jpa、junit、mapper、pagereqdto、reqdto、rsqdto、

