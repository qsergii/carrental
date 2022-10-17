package com.epam.carrental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContextListenerTest {

    @Test
    public void contextInitialized() {
        ServletContextEvent event = mock(ServletContextEvent.class);
        ServletContext context = mock(ServletContext.class);
        when(event.getServletContext())
                .thenReturn(context);
        when(context.getContextPath())
                .thenReturn("path");

        ContextListener contextListener = new ContextListener();
        Assertions.assertThrows(NullPointerException.class, () -> {
            contextListener.contextInitialized(event);
        });
    }

    @Test
    void contextDestroyed() {
        ServletContextEvent event = mock(ServletContextEvent.class);
        ContextListener contextListener = new ContextListener();
        contextListener.contextDestroyed(event);
    }

//    @Test
//    void checkDbConnection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Method method = ContextListener.class.getDeclaredMethod("checkDbConnection");
//        method.setAccessible(true);
//        method.invoke(null);
//    }
}
