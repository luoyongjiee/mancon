package com.efunds.market.mc.infrastructure.config;

import com.github.pagehelper.PageHelper;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by luoqi on 2015/12/12.
 */
@Configuration
@MapperScan(basePackages="com.efunds.market.mc.dao.iface")
public class MyBatisConfig {

    @Bean
    public DataSource dataSource() {

            BasicDataSource ds = new BasicDataSource();
            //  ds.setDriverClassLoader(properties.getClassLoader());
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/etrade");
            ds.setUsername("root");
            ds.setPassword("root");
            return ds;

    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        properties.put("dialect","mysql");
        properties.put("reasonable","false");
        pageHelper.setProperties(properties);
        return pageHelper;
    }


    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
        factoryBean.setPlugins(new Interceptor[]{pageHelper()});
        try {
            factoryBean.setMapperLocations(resolver.getResources(CLASSPATH_ALL_URL_PREFIX + "com/efunds/market/mc/dao/**/*.xml"));
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("SqlSessionFactory not found");
        }

    }

}
