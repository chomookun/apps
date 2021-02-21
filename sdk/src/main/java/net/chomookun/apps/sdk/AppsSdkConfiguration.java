package net.chomookun.apps.sdk;

import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.type.JdbcType;
import org.hibernate.cfg.AvailableSettings;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.extern.slf4j.Slf4j;
import net.chomookun.apps.sdk.core.FullBeanNameGenerator;
import net.chomookun.apps.sdk.core.RoutingDataSource;

@Configuration
@ComponentScan(nameGenerator = FullBeanNameGenerator.class, basePackages = { "net.chomookun.apps.sdk" })
@Slf4j
public class AppsSdkConfiguration implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContextObj) throws BeansException {
        applicationContext = applicationContextObj;
    }

    /**
     * returns application context
     * 
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Bean
    public AppsSdkConfig appsSdkConfig() throws Exception {
        Environment environment = applicationContext.getEnvironment();
        Binder binder = Binder.get(environment);
        AppsSdkConfig appsSdkConfig = binder.bind("apps.sdk", Bindable.of(AppsSdkConfig.class)).get();
        log.info("appsSdkConfig:{}", appsSdkConfig);
        return appsSdkConfig;
    }

    @Bean(destroyMethod = "close")
    public RoutingDataSource dataSource(@Qualifier("appsSdkConfig") AppsSdkConfig appsSdkConfig) throws Exception {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<String, HikariConfig> dataSources = appsSdkConfig.getDataSources();
        for (String key : dataSources.keySet()) {
            HikariConfig config = dataSources.get(key);
            // routingDataSource.addDataSource();
        }
        return routingDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {

        // creates LocalContainerEntityManagerFactoryBean instance.
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceUnitName("entityManagerFactory");

        // defines vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(AvailableSettings.HQL_BULK_ID_STRATEGY,
                "org.hibernate.hql.spi.id.inline.InlineIdsOrClauseBulkIdStrategy"); // Bulk-id strategies when you can’t
                                                                                    // use temporary tables
        jpaProperties.setProperty(AvailableSettings.SHOW_SQL, Boolean.toString(true));
        jpaProperties.setProperty(AvailableSettings.FORMAT_SQL, Boolean.toString(true));
        jpaProperties.setProperty(AvailableSettings.DEFAULT_NULL_ORDERING, "last");

        vendorAdapter.setDatabasePlatform(org.hibernate.dialect.MySQLDialect.class.getName());
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPackagesToScan(this.getClass().getPackageName());

        // return
        return entityManagerFactory;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // sets configurations
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(true);
        configuration.setCallSettersOnNulls(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Slf4jImpl.class);

        // enable lazy loading
        configuration.setLazyLoadingEnabled(true);
        configuration.setAggressiveLazyLoading(false);
        configuration.getLazyLoadTriggerMethods().clear();

        // invokes afterPropertiesSet method
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.afterPropertiesSet();
        return sqlSessionFactoryBean;
    }

}
