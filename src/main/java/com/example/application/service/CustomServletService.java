package com.example.application.service;

import java.io.IOException;
import java.util.List;

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.HandlerHelper;
import com.vaadin.flow.server.RequestHandler;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.communication.PushRequestHandler;

public class CustomServletService extends VaadinServletService {

    public CustomServletService(VaadinServlet servlet, DeploymentConfiguration deploymentConfiguration) {
        super(servlet, deploymentConfiguration);
    }

    @Override
    protected List<RequestHandler> createRequestHandlers() throws ServiceException {
        List<RequestHandler> requestHandlers = super.createRequestHandlers();
        requestHandlers.add(new CustomPushRequestHandler(this));
        return requestHandlers;
    }

    private static class CustomPushRequestHandler extends PushRequestHandler {

        /**
         * Creates an instance connected to the given service.
         *
         * @param service the service this handler belongs to
         * @throws ServiceException if initialization of Atmosphere fails
         */
        public CustomPushRequestHandler(VaadinServletService service) throws ServiceException {
            super(service);
        }

        @Override
        public boolean handleRequest(VaadinSession session, VaadinRequest request, VaadinResponse response)
                throws IOException {
            boolean result = super.handleRequest(session, request, response);
            if (HandlerHelper.isRequestType(request, HandlerHelper.RequestType.PUSH)) {
                if (isWebsocketRequest(request)) {
                    throw new RuntimeException("Prevented websocket connection");
                }
            }
            return result;
        }

        private boolean isWebsocketRequest(VaadinRequest request) {
            return request != null && "websocket".equals(request.getParameter("X-Atmosphere-Transport"))
                    && request.getParameter("debug_window") == null;
        }
    }
}
