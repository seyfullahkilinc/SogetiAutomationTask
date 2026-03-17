package org.example.utils;

import com.github.javafaker.Faker;

/**
 * FakerUtils — Centralized random test data generator.
 *
 * WHY A SEPARATE UTILITY CLASS?
 * - Single Responsibility: data generation logic lives in one place.
 * - Reusability: any Step Definition or Page can call it without duplicating code.
 * - Maintainability: if the Faker library changes, only this class needs updating.
 */
public class FakerUtils {

    private static final Faker faker = new Faker();

    public static String getFirstName()   { return faker.name().firstName(); }
    public static String getLastName()    { return faker.name().lastName(); }
    public static String getJobTitle()    { return faker.job().title(); }
    public static String getEmail()       { return faker.internet().emailAddress(); }
    public static String getCompanyName() { return faker.company().name(); }
    public static String getPhoneNumber() { return faker.number().digits(10); }
    public static String getMessage()     { return faker.lorem().sentence(12); }
}
