package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class QualityEngineeringPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public QualityEngineeringPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isTextVisible(String text) {
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'" + text + "')]")
                    )
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}