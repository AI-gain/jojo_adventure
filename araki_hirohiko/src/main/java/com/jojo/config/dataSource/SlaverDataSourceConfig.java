package com.jojo.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描mapper接口并容器化管理
@MapperScan(basePackages = SlaverDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "slaverSqlSessionFactory")
public class SlaverDataSourceConfig {
    // 精确到 slaver 目录，以便和其他数据源区分
    static final String PACKAGE = "com.jojo.dao.slaver";
    static final String MAPPER_LOCATION = "classpath:/mapper/slaver/*.xml";

    @Value("${slaver.datasource.url}")
    private String url;

    @Value("${slaver.datasource.username}")
    private String user;

    @Value("${slaver.datasource.password}")
    private String password;

    @Value("${slaver.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "slaverDataSource")
    public DataSource slaverDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "slaverTransactionManager")
    public DataSourceTransactionManager slaverTransactionManager() {
        return new DataSourceTransactionManager(slaverDataSource());
    }

    @Bean(name = "slaverSqlSessionFactory")
    public SqlSessionFactory slaverSqlSessionFactory(@Qualifier("slaverDataSource") DataSource slaverDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(slaverDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SlaverDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
