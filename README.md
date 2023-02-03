# unique-id-generator

封装百度的 [uid-generator](https://github.com/baidu/uid-generator) ，使其可以快速集成到 spring-boot 项目中

## 使用说明

- 1、在数据库中建表 参考 `src/main/resources/META-INF/WORKER_NODE.sql`

- 2、集成依赖（需先将该项目源码下载并打包）
```xml
<dependency>
    <groupId>com.mogudiandian</groupId>
    <artifactId>unique-id-generator</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

- 3、在启动类上引用注解 `@EnableUniqueIDGenerator`
```java
@EnableUniqueIDGenerator
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
```

- 4、加入配置[可选，如果是用spring-boot默认的数据源或数据源的beanName为dataSource则不需要这一步]
```properties
unique.id.generator.datasource.name=WORKER_NODE表所在库的数据源名称
```

## 使用前准备

- [Maven](https://maven.apache.org/) (构建/发布当前项目)
- Java 8 ([Download](https://adoptopenjdk.net/releases.html?variant=openjdk8))

## 构建/安装项目

使用以下命令:

`mvn clean install`

## 引用项目

```xml

<dependency>
    <groupId>com.mogudiandian</groupId>
    <artifactId>joshua-aop</artifactId>
    <version>LATEST</version>
</dependency>
```

## 发布项目

修改 `pom.xml` 的 `distributionManagement` 节点，替换为自己在 `settings.xml` 中 配置的 `server` 节点，
然后执行 `mvn clean deploy`

举例：

`settings.xml`

```xml
<servers>
    <server>
        <id>snapshots</id>
        <username>yyy</username>
        <password>yyy</password>
    </server>
    <server>
        <id>releases</id>
        <username>xxx</username>
        <password>xxx</password>
    </server>
</servers>
```

`pom.xml`

```xml
<distributionManagement>
    <snapshotRepository>
        <id>snapshots</id>
        <url>http://xxx/snapshots</url>
    </snapshotRepository>
    <repository>
        <id>releases</id>
        <url>http://xxx/releases</url>
    </repository>
</distributionManagement>
```
## 原理
请参考原始仓库 [uid-generator中文文档](https://github.com/baidu/uid-generator/blob/master/README.zh_cn.md)
