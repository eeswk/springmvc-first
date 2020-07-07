package ee.swan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import ee.swan.controller.Cart;
import ee.swan.exception.CustomCallableProcessingInterceptor;
import ee.swan.exception.CustomDeferredResultProcessingInterceptor;
import ee.swan.interceptor.SuccessLoggingInterceptor;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan("ee.swan")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp();
    }

    @Bean
    OptionalValidatorFactoryBean validator() {
        OptionalValidatorFactoryBean validator = new OptionalValidatorFactoryBean();
        //설정자 메서드를 이용해 커스터마이징한다.
        return validator;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(
                Jackson2ObjectMapperBuilder.json().indentOutput(true).build());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, mappingJackson2HttpMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
    }

    @Bean
    ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                //옵션을 지정
                .indentOutput(true)
                .dateFormat(new StdDateFormat())
                .build();
    }

    @Bean
    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(3000);
        return new RestTemplate(requestFactory);
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Cart cart() {
        return new Cart();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @Bean
    public AsyncTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(mvcTaskExecutor());

        configurer.registerCallableInterceptors(new CustomCallableProcessingInterceptor());
        configurer.registerDeferredResultInterceptors(new CustomDeferredResultProcessingInterceptor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SuccessLoggingInterceptor())
                .addPathPatterns("/**") // 적용대상 경로를 지정
                .excludePathPatterns("/resources/**"); //제외 경로지정
    }
}
