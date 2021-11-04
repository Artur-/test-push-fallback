package com.example.application.service;

import org.springframework.context.ApplicationContext;

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.spring.SpringServlet;

public class CustomServlet extends SpringServlet {

    public CustomServlet(ApplicationContext context) {
        super(context, false);
    }

    @Override
    protected VaadinServletService createServletService(
            DeploymentConfiguration deploymentConfiguration)
            throws ServiceException {
        CustomServletService service = new CustomServletService(this,
                deploymentConfiguration);
        service.init();
        return service;
    }
}
