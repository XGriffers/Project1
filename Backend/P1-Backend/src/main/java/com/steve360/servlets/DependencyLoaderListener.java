package com.steve360.servlets;

import com.steve360.Services.ManagerService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;




public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("initializing...");
        ManagerService.getConnection();
        System.out.println("Init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}