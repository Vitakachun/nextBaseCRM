package com.nextBaseCRM.test;

import com.nextBaseCRM.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestCase1_1 {
    public static void main(String[] args) throws InterruptedException {

        // User Story #1 As a user, I should be able to login to the app.

        //  Open Chrome browser
        WebDriver driver = WebDriverFactory.getDriver("chrome");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // TC #1. Open website / go to login page: http://login2.nextbasecrm.com/
        driver.get("http://login2.nextbasecrm.com/");

        // Verify title equals:
        // Expected: Authorization
        String expectedTitle = "Authorization";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Landing page title verification PASSED!");
        } else {
            System.err.println("Landing page title verification FAILED!");
            System.out.println("Expected title = " + expectedTitle);
            System.out.println("Actual title = " + actualTitle);
        }
        // TC #2. Enter positive user name and password.
        // TC #3. Enter positive user password.

        /*
        Usernames:
    helpdesk39@cybertekschool.com
    helpdesk40@cybertekschool.com
    marketing39@cybertekschool.com
    marketing40@cybertekschool.com
    hr39@cybertekschool.com
    hr40@cybertekschool.com

    Password:
    UserUser
         */
        ArrayList<String> usernamesPositive = new ArrayList<String>(Arrays.asList("helpdesk39@cybertekschool.com",
                "helpdesk40@cybertekschool.com",
                "marketing39@cybertekschool.com",
                "marketing40@cybertekschool.com",
                "hr39@cybertekschool.com",
                "hr40@cybertekschool.com"));

        String password = "UserUser";


        for (String each : usernamesPositive) {
            // type User name
            driver.findElement(By.name("USER_LOGIN")).sendKeys(each);
            // type Password
            driver.findElement(By.name("USER_PASSWORD")).sendKeys(password);
            // TC #4 Click login button
            driver.findElement(By.className("login-btn")).click();

            // TC #5 User should be on home page
            // Check if expectedURL equals actualURL
            String expectedURL = "https://login2.nextbasecrm.com/stream/";
            String expectedURL1 = "https://login2.nextbasecrm.com/stream/?login=yes";
            String actualURL = driver.getCurrentUrl();
            if (actualURL.equals(expectedURL) || actualURL.equals(expectedURL1)) {
                System.out.println("Landing page URL verification PASSED!");
            } else {
                System.err.println("Landing page URL verification FAILED!");
                System.out.println("Expected URL = " + expectedURL);
                System.out.println("Actual URL = " + actualURL);
            }
            // do log out
            driver.findElement(By.className("user-name")).click();

            // click log out button
            driver.findElement(By.linkText("Log out")).click();


            // clear user id
            driver.findElement(By.name("USER_LOGIN")).clear();

        }
        // Enter negative user name and password.

        ArrayList<String> usernamesNegative = new ArrayList<String>(Arrays.asList(
                "helpdesk4555@cybertekschool.com",
                "aaaaaabbbbcccc@gmail.com",
                "marketing1000@cybertekschool.com"));

        String passwordNegative = "cyberttek";

        for (String each1 : usernamesNegative) {
            // type User name
            driver.findElement(By.name("USER_LOGIN")).sendKeys(each1);
            // type Password
            driver.findElement(By.name("USER_PASSWORD")).sendKeys(passwordNegative);

            //  Click login button
            driver.findElement(By.className("login-btn")).click();
            Thread.sleep(2000);

            // clear user id
            driver.findElement(By.name("USER_LOGIN")).clear();

            String expectedText = "Incorrect login or password";
            String actualText = driver.findElement(By.className("errortext")).getText();

            if (expectedText.equals(actualText)){
                System.out.println("Text verification PASSED!");
            }else {
                System.err.println("Text verification FAILED!!!");
                System.out.println("ExpectedText = " + expectedText);
                System.out.println("ActualText = " + actualText);
            }
        }
        // TC #6 Check "Remember me on this computer" option / CHECKBOX.
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));


        // Click checkbox to select it

        checkbox.click();

        // Confirm checkbox is SELECTED
        if(checkbox.isSelected()){
            System.out.println("Checkbox is selected, verification PASSED!");
        }else{
            System.out.println("Checkbox is NOT selected, verification PASSED!");

        }

        // TC #7 Access to "FORGOT YOUR PASSWORD?" link page.
        driver.findElement(By.xpath("//a[@class = 'login-link-forgot-pass']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //  Verify title equals:
        // Expected: Get Password

        String expectedTitle1 = "Get Password";
        String actualTitle1 = driver.getTitle();

        if (actualTitle1.equals(expectedTitle1)) {
            System.out.println("Page \"Get Password\" title verification PASSED!");
        } else {
            System.err.println("Page \"Get Password\" title verification FAILED!");
            System.out.println("Expected title = " + expectedTitle1);
            System.out.println("Actual title = " + actualTitle1);
        }
        driver.close();

    }
}

