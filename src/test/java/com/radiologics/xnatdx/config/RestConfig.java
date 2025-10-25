package com.radiologics.morpheus.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.nrg.framework.services.ContextService;
import org.nrg.xdat.security.services.PermissionsServiceI;
import org.nrg.xdat.security.services.RoleHolder;
import org.nrg.xdat.security.services.RoleServiceI;
import org.nrg.xdat.security.services.UserManagementServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.radiologics.morpheus.exceptions.NotUniqueException;
import com.radiologics.morpheus.service.UserStorageService;
import com.radiologics.morpheus.service.UserStorageServiceImpl;
import com.radiologics.morpheus.storage.daos.UserStorageDao;
import com.radiologics.morpheus.storage.entity.UserStorageEntity;
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableWebSecurity
public class RestConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
     
    
    @Bean
    public Properties hibernateProperties() throws IOException {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.cache.use_query_cache", false);

        PropertiesFactoryBean hibernate = new PropertiesFactoryBean();
        hibernate.setProperties(properties);
        hibernate.afterPropertiesSet();
        return hibernate.getObject();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(org.h2.Driver.class.getName());
        basicDataSource.setUrl("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        basicDataSource.setUsername("sa");
        return basicDataSource;
    }
    

    
    @Bean
    public LocalSessionFactoryBean sessionFactory(final DataSource dataSource, @Qualifier("hibernateProperties") final Properties properties) {
        final LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setHibernateProperties(properties);
        bean.setAnnotatedClasses(
                UserStorageEntity.class);
        return bean;
    }
    
    

    @Bean
    public UserManagementServiceI mockUserManagementServiceI() {
        return Mockito.mock(UserManagementServiceI.class);
    }
    @Bean
    @Qualifier("mockRoleService")
    public RoleServiceI mockRoleService() {
        return Mockito.mock(RoleServiceI.class);
    }

    @Bean
    public RoleHolder mockRoleHolder(@Qualifier("mockRoleService") final RoleServiceI roleServiceI,
                                     final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new RoleHolder(roleServiceI, namedParameterJdbcTemplate);
    }
    @Bean
    public NamedParameterJdbcTemplate mockNamedParameterJdbcTemplate() {
        return Mockito.mock(NamedParameterJdbcTemplate.class);
    }
    @Bean
    public ResourceTransactionManager transactionManager(final SessionFactory sessionFactory) throws Exception {
        return new HibernateTransactionManager(sessionFactory);
    }
    @Bean
    public UserStorageService userStorageService() throws Exception {
        return new UserStorageServiceImpl();
    }
    @Bean
    public UserStorageDao userStorageDao() throws Exception {
        return new UserStorageDao();
    }
    
    @Bean
    public PermissionsServiceI permissionsService() {
        return Mockito.mock(PermissionsServiceI.class);
    }

    @Bean
    public ContextService contextService(final ApplicationContext applicationContext) {
        final ContextService contextService = new ContextService();
        contextService.setApplicationContext(applicationContext);
        return contextService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new TestingAuthenticationProvider());
    }
    
    
  
}
