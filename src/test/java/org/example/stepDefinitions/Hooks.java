package org.example.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.utils.DriverManager;

public class Hooks {
    @Before("@UI")
    public void setUp() {
        DriverManager.getDriver();
    }

    @After("@UI")
    public void tearDown() {
        DriverManager.quitDriver();
    }
}