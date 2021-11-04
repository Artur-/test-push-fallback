package com.example.application;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class HelloWorldView extends VerticalLayout {

    private VerticalLayout pushContainer;

    public HelloWorldView() {
        pushContainer = new VerticalLayout();
        Button push = new Button("Hello", click -> Notification.show("Hello"));
        add(push, pushContainer);
        push();
    }

    private void push() {
        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                final int counter = i;
                getUI().ifPresent(ui -> ui.access(() -> {
                    Div div = new Div();
                    div.setText("Push message " + counter);
                    pushContainer.add(div);
                }));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        };
        new Thread(runnable).start();
    }

}
