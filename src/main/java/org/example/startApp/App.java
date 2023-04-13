package org.example.startApp;

import org.example.services.SecondThread.PrintUsersImpl;
import org.example.services.printInfo.PrintInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
//    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        PrintInfo p = new PrintInfo();
        p.print();
        p.start();

    }
}