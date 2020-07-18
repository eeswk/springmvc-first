package ee.swan.config;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
/*
    @Autowired
    DataSource dataSource;

    @Bean
    public DataSource dataSource() throws NamingException {
        //JndiTemplate jndiTemplate = new JndiTemplate();
        //return jndiTemplate.lookup("jdbc/dataSource", DataSource.class);
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                //.addScript("schema.sql")
                .build();
    }
 */
    /*
        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setDatabase(Database.H2);
            vendorAdapter.setShowSql(true);
            return vendorAdapter;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setDataSource(dataSource);
            factory.setPackagesToScan("ee.swan.domain");
            factory.setJpaVendorAdapter(jpaVendorAdapter());
            return factory;
        }
    */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("jpabook");
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public EntityTransaction entityTransaction() {
        return entityManager().getTransaction();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory());
        //tm.setDataSource(dataSource);
        //System.out.println("궁금=>"+tm.getDataSource());
        return tm;
    }

/*
    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer().start();
    }
 */
}
