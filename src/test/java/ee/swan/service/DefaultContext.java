package ee.swan.service;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiTemplate;

@Configuration
@Profile("default")
public class DefaultContext {

    @Bean
    DataSource dataSource() throws NamingException {
        System.out.println("defalut = jndiTemplate");
        JndiTemplate jndiTemplate = new JndiTemplate();
        return jndiTemplate.lookup("jdbc/dataSource", DataSource.class);
    }
}
