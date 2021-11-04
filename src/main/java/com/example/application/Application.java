package com.example.application;

import com.example.application.service.CustomServlet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.spring.SpringServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.helpers.LaunchUtil;

@Push
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @Bean
    public ServletRegistrationBean<SpringServlet> springServlet(ApplicationContext context) {
        return new ServletRegistrationBean<>(new CustomServlet(context), "/*", "/frontend/*");
    }

}
