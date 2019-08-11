package com.belenot.mirea.schedule.config;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import com.belenot.mirea.schedule.ScheduleParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan( "com.belenot.mirea.schedule" )
@PropertySource( "classpath:server.properties" )
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;
    
    @Bean
    public ScheduleParser scheduleParser() throws Exception {
	InputStream in = new ClassPathResource("kbisp.xlsx").getInputStream();
	ScheduleParser scheduleParcer = new ScheduleParser(in);
	return scheduleParcer;
    }

    @Autowired
    private SessionFactoryConfig sessionFactoryConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//registry.addResourceHandler("/").addResourceLocations(env.getProperty("/html/index.html"));
	registry.addResourceHandler("/**/*.{[a-z]+}").addResourceLocations("/resources/misc/");
	registry.addResourceHandler("/**/*.html").addResourceLocations("/resources/html/");
	registry.addResourceHandler("/**/*.js").addResourceLocations("/resources/js/");
	registry.addResourceHandler("/**/*.map").addResourceLocations("/resources/js/");
	registry.addResourceHandler("/**/*.css").addResourceLocations("/resources/css/");
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	transactionManager.setSessionFactory(sessionFactoryConfig.sessionFactory());
	return transactionManager;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
	    .indentOutput(true)
	    .dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
	converters.add(converter);
    }
    
}
