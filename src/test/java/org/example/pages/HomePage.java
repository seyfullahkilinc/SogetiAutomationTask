package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void handlePopups() {
        // Germany redirect popup
        try {
            WebElement stayOnSite = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Stay on this Site')]")
                    )
            );
            stayOnSite.click();
            Thread.sleep(1000);
        } catch (Exception e) {

        }

        // Cookie popup
        try {
            WebElement acceptButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.id("accept")
                    ));
            acceptButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }

    public void hoverOverElement(String linkText) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space()='" + linkText + "']")
                )
        );
        actions.moveToElement(element).perform();
    }

    public void clickElement(String linkText) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[normalize-space()='" + linkText + "']")
                )
        );
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public boolean isTextVisible(String text) {
        try {
            // Önce cookie popup'ı kapat
            handlePopups();
            Thread.sleep(2000);

            // Önce normal text ara
            List<WebElement> elements = driver.findElements(
                    By.xpath("//*[contains(text(),'" + text + "')]")
            );

            // Bulunamazsa aria-label veya class içinde ara
            if (elements.isEmpty()) {
                elements = driver.findElements(
                        By.xpath("//*[contains(@class,'" + text.toLowerCase().replace(" ", "-") + "')]")
                );
            }

            System.out.println("Elements found: " + elements.size());
            return !elements.isEmpty();

        } catch (Exception e) {
            System.out.println("Text not found: " + text);
            System.out.println("Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }
}