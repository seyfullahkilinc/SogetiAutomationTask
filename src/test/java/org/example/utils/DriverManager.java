package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private static final boolean HEADLESS = Boolean.parseBoolean(
            System.getProperty("headless", "false")
    );

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.OFF);
            System.setProperty("webdriver.chrome.silentOutput", "true");

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-logging");
            options.addArguments("--log-level=3");

            if (HEADLESS) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
            } else {
                options.addArguments("--start-maximized");
            }

            driverThreadLocal.set(new ChromeDriver(options));
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}