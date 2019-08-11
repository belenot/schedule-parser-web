package com.belenot.mirea.schedule;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.belenot.mirea.schedule.config.WebAppConfig;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(WebAppConfig.class);
	ServletRegistration.Dynamic servlet = servletContext.addServlet("schedule", new DispatcherServlet(ctx));
	servlet.setLoadOnStartup(1);
	servlet.addMapping("/");
	
    }


}
