package org.example.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}