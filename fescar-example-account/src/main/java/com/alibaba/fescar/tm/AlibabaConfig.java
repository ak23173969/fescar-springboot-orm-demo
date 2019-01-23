package com.alibaba.fescar.tm;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fescar.rm.RMClientAT;
import com.alibaba.fescar.rm.datasource.DataSourceProxy;
import com.alibaba.fescar.spring.annotation.GlobalTransactionScanner;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:17:16   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@Configuration
public class AlibabaConfig implements BeanPostProcessor {

	@Autowired
	private Environment environment;

	@Value("${spring.dubbo.application.name}")
	private String dubboApplicaitonId;

	@Value("${dubbo.qos.port}")
	private Integer qosPort;

	@Primary
	@Bean(name = "writeDataSource")
	public DataSource writeDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		try {
			dataSource.setUrl(this.environment.getProperty("spring.datasource.url"));
			dataSource.setUsername(this.environment.getProperty("spring.datasource.username"));
			dataSource.setPassword(this.environment.getProperty("spring.datasource.password"));
			dataSource.setInitialSize(Integer.parseInt(this.environment.getProperty("spring.datasource.initialSize")));
			dataSource.setMinIdle(Integer.parseInt(this.environment.getProperty("spring.datasource.minIdle")));
			dataSource.setMaxActive(Integer.parseInt(this.environment.getProperty("spring.datasource.maxActive")));
			dataSource.setMaxWait(Long.parseLong(this.environment.getProperty("spring.datasource.maxWait")));
			dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(this.environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
			dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(this.environment.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
			dataSource.setValidationQuery(this.environment.getProperty("spring.datasource.validationQuery"));
			dataSource.setTestWhileIdle(Boolean.parseBoolean(this.environment.getProperty("spring.datasource.testWhileIdle")));
			dataSource.setTestOnBorrow(Boolean.parseBoolean(this.environment.getProperty("spring.datasource.testOnBorrow")));
			dataSource.setTestOnReturn(Boolean.parseBoolean(this.environment.getProperty("spring.datasource.testOnReturn")));
			dataSource.setConnectionProperties(this.environment.getProperty("spring.datasource.connectionProperties"));
			dataSource.setFilters(this.environment.getProperty("spring.datasource.filters"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean(name = "accountDataSourceProxy")
	public DataSourceProxy initProxy(@Qualifier("writeDataSource") DataSource druidDatasource) {
		return new DataSourceProxy((DruidDataSource) druidDatasource);
	}

	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;

	@Value("${mybatis.type-aliases-package}")
	private String typeAliasePackage;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasePackage);
		sqlSessionFactoryBean.setDataSource(dataSourceProxy);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public GlobalTransactionScanner initGlobalTransactionScanner() {
		return new GlobalTransactionScanner(dubboApplicaitonId, "my_test_tx_group");
	}

	@PostConstruct
	public void initFescerClient() {
		System.setProperty("dubbo.qos.port", String.valueOf(qosPort));
		String txServiceGroup = "my_test_tx_group";
		RMClientAT.init(dubboApplicaitonId, txServiceGroup);
	}

	@Bean
	public JdbcTemplate initJdbcTemplate(@Qualifier("accountDataSourceProxy") DataSourceProxy dataSourceProxy) {
		return new JdbcTemplate(dataSourceProxy);
	}

}
