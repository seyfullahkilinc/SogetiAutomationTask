package org.example.stepDefinitions;

import io.cucumber.java.en.Then;
import org.example.pages.HomePage;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.util.List;

public class TC3StepDefinitions {

    private final HomePage homePage = new HomePage(DriverManager.getDriver());
    @Then("Assert that all the Country specific Sogeti links are working")
    public void verifyAllCountryLinks() {
        List<WebElement> links = homePage.getCountryLinks();
        SoftAssert softAssert = new SoftAssert();

        System.out.println("=================================================");
        System.out.println("Total country links found: " + links.size());
        System.out.println("=================================================");

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty() || url.contains("javascript")) continue;

            try {
                HttpURLConnection connection = (HttpURLConnection) java.net.URI.create(url).toURL().openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int responseCode = connection.getResponseCode();

                String status = responseCode < 400 ? "PASS" : "FAIL";

                String host = java.net.URI.create(url).getHost();

                System.out.printf("[%s] %-25s | Status Code: %d%n", status, host, responseCode);

                softAssert.assertTrue(responseCode < 400, "Link failed: " + host);
            } catch (Exception e) {
                softAssert.fail("Connection error for: " + url);
            }
        }
        System.out.println("=================================================");
        softAssert.assertAll();
    }
}
