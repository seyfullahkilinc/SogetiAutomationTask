package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void handlePopups() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script =
                    "function findBtn(root) {" +
                            "  const b = root.querySelector('button[data-testid=\"uc-accept-all-button\"]') || root.querySelector('#accept');" +
                            "  if (b) { b.click(); return true; }" +
                            "  const hs = root.querySelectorAll('*');" +
                            "  for (let h of hs) { if (h.shadowRoot && findBtn(h.shadowRoot)) return true; }" +
                            "  return false;" +
                            "}" +
                            "return findBtn(document);";
            js.executeScript(script);

        } catch (Exception ignored) {}

        try {
            By stayBtnLocator = By.xpath("//button[contains(text(),'Stay on this Site')]");
            WebElement stayBtn = wait.until(ExpectedConditions.elementToBeClickable(stayBtnLocator));
            stayBtn.click();
        } catch (Exception ignored) {
        }
    }
    public boolean isTextVisible(String text) {
        try {
            handlePopups();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + text + "')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}