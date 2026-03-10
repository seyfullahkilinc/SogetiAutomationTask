# SogetiAutomationTask

## Project Coding Rules
- All Selenium methods must be **generic and reusable**
- Locators must accept **parameters** (no hardcoded values)
- Feature files must use **{string}** parameters in steps
- One method should handle multiple inputs (avoid duplicate methods)
- Use **Page Object Model (POM)** pattern for all pages

## Tech Stack
- Java 19
- Selenium 4.18.0
- Cucumber 7.15.0
- TestNG 7.9.0
- REST Assured 5.4.0
- JavaFaker 1.0.2
- WebDriverManager 5.7.0

## Test Cases
- **TC1:** Navigate to Quality Engineering page via Services menu
- **TC2:** Contact Us form submission with random data
- **TC3:** Country specific Sogeti links validation
- **API1:** Zippopotam.us API tests
- **API2:** Zippopotam.us Data Driven API tests

## How to Run
```bash
mvn test
```