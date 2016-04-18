package ru.outofrange.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("ru.outofrange")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages ="ru.outofrange.repository")
public class WebAppConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_CACHE_REGION_CLASS="hibernate.cache.region.factory_class";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_NAME_HBM2DDL="hbm2ddl.auto";
	
	private static final String PROPERTY_NAME_CACHE_PROVIDER_CLASS = "hibernate.cache.provider_class";
	private static final String PROPERTY_NAME_CACHE_USE_SECOND_LEVEL = "hibernate.cache.use_second_level_cache";
	private static final String PROPERTY_NAME_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
	private static final String PROPERTY_NAME_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
	
	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,	env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_CACHE_REGION_CLASS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_REGION_CLASS));
		properties.put(PROPERTY_NAME_HBM2DDL, env.getRequiredProperty(PROPERTY_NAME_HBM2DDL));
		
		properties.put(PROPERTY_NAME_CACHE_PROVIDER_CLASS, env.getRequiredProperty(PROPERTY_NAME_CACHE_PROVIDER_CLASS));
		properties.put(PROPERTY_NAME_CACHE_USE_SECOND_LEVEL, env.getRequiredProperty(PROPERTY_NAME_CACHE_USE_SECOND_LEVEL));
		properties.put(PROPERTY_NAME_USE_QUERY_CACHE, env.getRequiredProperty(PROPERTY_NAME_USE_QUERY_CACHE));
		properties.put(PROPERTY_NAME_SESSION_CONTEXT_CLASS, env.getRequiredProperty(PROPERTY_NAME_SESSION_CONTEXT_CLASS));
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(env.getRequiredProperty("message.source.basename"));
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
}
