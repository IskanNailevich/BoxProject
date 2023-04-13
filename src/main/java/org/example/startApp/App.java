package org.example.startApp;

import org.apache.log4j.BasicConfigurator;
import org.example.services.printInfo.PrintInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        PrintInfo p = new PrintInfo();
        LOGGER.info("Start app");
        p.print();
        p.start();

    }
}