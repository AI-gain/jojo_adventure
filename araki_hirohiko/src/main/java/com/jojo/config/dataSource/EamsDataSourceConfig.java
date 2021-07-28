package com.jojo.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描mapper接口并容器化管理
@MapperScan(basePackages = EamsDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "eamsSqlSessionFactory")
public class EamsDataSourceConfig {
    // 精确到 eams 目录，以便和其他数据源区分
    static final String PACKAGE = "com.jojo.dao.eams";
    static final String MAPPER_LOCATION = "classpath:/mapper/eams/*.xml";

    @Value("${eams.datasource.url}")
    private String url;

    @Value("${eams.datasource.username}")
    private String user;

    @Value("${eams.datasource.password}")
    private String password;

    @Value("${eams.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "eamsDataSource")
    public DataSource eamsDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "eamsTransactionManager")
    public DataSourceTransactionManager eamsTransactionManager() {
        return new DataSourceTransactionManager(eamsDataSource());
    }

    @Bean(name = "eamsSqlSessionFactory")
    public SqlSessionFactory eamsSqlSessionFactory(@Qualifier("eamsDataSource") DataSource eamsDataSource)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(eamsDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(EamsDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
