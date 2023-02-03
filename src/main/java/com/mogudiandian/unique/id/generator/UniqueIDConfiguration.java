package com.mogudiandian.unique.id.generator;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 唯一ID生成器
 * @author sunbo
 */
@Configuration
@MapperScan(basePackages = "com.baidu.fsg.uid.worker.dao",
        sqlSessionFactoryRef = "uniqueIdGeneratorSqlSessionFactory",
        sqlSessionTemplateRef = "uniqueIdGeneratorSqlSessionTemplate")
public class UniqueIDConfiguration {

    @Resource(name = "${unique.id.generator.datasource.name:dataSource}")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory uniqueIdGeneratorSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:baidu-uid-generator/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate uniqueIdGeneratorSqlSessionTemplate(@Qualifier("uniqueIdGeneratorSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public TransactionManager uniqueIdGeneratorTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
    public WorkerIdAssigner uniqueIdGeneratorWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Bean
    public UidGenerator uidGenerator(@Qualifier("uniqueIdGeneratorWorkerIdAssigner") WorkerIdAssigner workerIdAssigner) {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(workerIdAssigner);
        cachedUidGenerator.setEpochStr("2021-11-25");
        return cachedUidGenerator;
    }

    @Bean
    public UniqueIDGenerator uniqueIDGenerator(@Qualifier("uidGenerator") UidGenerator uidGenerator) {
        return new DefaultUniqueIDGenerator(uidGenerator);
    }

}
