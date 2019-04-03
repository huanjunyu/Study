package com.order;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jolbox.bonecp.BoneCPDataSource;

//此注解表示这个类是一个配置类，相当于一个xml文件
@Configuration
@PropertySource(value="classpath:jdbc.properties",ignoreResourceNotFound=true)
public class DatasourceConfig {

    //@Value("${url}")读取属性文件中的url键的值，并赋值到jdbcUrl中
	@Value("${url}")
	private String jdbcUrl;

	@Value("${driver}")
	private String jdbcDriverClassName;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${password}")
	private String jdbcPassword;

	//@Bean注解表示修饰的方法返回类型会自动创建对象，存储在IOC容器中
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		System.out.println(jdbcDriverClassName+jdbcUrl+jdbcUsername+jdbcPassword);
		BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
		// 数据库驱动
		boneCPDataSource.setDriverClass(jdbcDriverClassName);
		// 相应驱动的jdbcUrl
		boneCPDataSource.setJdbcUrl(jdbcUrl);
		// 数据库的用户名
		boneCPDataSource.setUsername(jdbcUsername);
		// 数据库的密码
		boneCPDataSource.setPassword(jdbcPassword);
		// 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
		boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
		// 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
		boneCPDataSource.setIdleMaxAgeInMinutes(30);
		// 每个分区最大的连接数
		boneCPDataSource.setMaxConnectionsPerPartition(100);
		// 每个分区最小的连接数
		boneCPDataSource.setMinConnectionsPerPartition(5);
		return boneCPDataSource;
	}
}
