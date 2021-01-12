package com.nextBaseCRM.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class UserStory1 {
    WebDriver driver;
    @BeforeMethod
    public void netBaseLoginPage() throws InterruptedException {
        //  Open Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://login2.nextbasecrm.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //  Verify title equals
        //  Expected: Authorization
        String expectedTitle = "Authorization";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Landing page title verification PASSED!");
        } else {
            System.err.println("Landing page title verification FAILED!");
            System.out.println("Expected title = " + expectedTitle);
            System.out.println("Actual title = " + actualTitle);
            Thread.sleep(3000);
        }
    }
    //=======================================================================================
    // TC #1. Enter positive user name and positive user password.
    /*
Usernames: helpdesk49@cybertekschool.com
           helpdesk50@cybertekschool.com
           marketing49@cybertekschool.com
           marketing50@cybertekschool.com
           hr49@cybertekschool.com
           hr50@cybertekschool.com
Password:  UserUser
     */
    //==========================================================================
    // TC #1. Enter positive user name and positive user password.
    @Test
    public void TC1positiveDataTest() throws InterruptedException {
        ArrayList<String> usernamesPositive = new ArrayList<String>(Arrays.asList("helpdesk49@cybertekschool.com",
                "helpdesk50@cybertekschool.com",
                "marketing49@cybertekschool.com",
                "marketing50@cybertekschool.com",
                "hr49@cybertekschool.com",
                "hr50@cybertekschool.com"));
        String password = "UserUser";
        for (String each : usernamesPositive) {
            // type User name
            driver.findElement(By.name("USER_LOGIN")).sendKeys(each);
            // type Password
            driver.findElement(By.name("USER_PASSWORD")).sendKeys(password);
            //====================================================================================
            // TC #4 Click login button
            driver.findElement(By.className("login-btn")).click();
            // do log out
            driver.findElement(By.className("user-name")).click();
            // click log out button
            driver.findElement(By.linkText("Log out")).click();
            // clear user id
            driver.findElement(By.name("USER_LOGIN")).clear();
        }
    }
    //==========================================================================
    // TC #2. Enter negative user name and negative user password.
    @Test
    public void TC2negativeDataTest() throws InterruptedException {
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
            if (expectedText.equals(actualText)) {
                System.out.println("Text verification PASSED!");
            } else {
                System.err.println("Text verification FAILED!");
                System.out.println("ExpectedText = " + expectedText);
                System.out.println("ActualText = " + actualText);
            }
        }
    }
    //=======================================================================================
    @Test
    // TC #3 Check "Remember me on this computer" option / CHECKBOX.
    public void TC3checkBox() throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        Thread.sleep(2000);
        // Click checkbox to select it
        checkbox.click();
        // Confirm checkbox is SELECTED
        if (checkbox.isSelected()) {
            System.out.println("Checkbox is selected, verification PASSED!");
        } else {
            System.out.println("Checkbox is NOT selected, verification PASSED!");
        }
    }
    //==========================================================================
    @Test// TC #4 Access to "FORGOT YOUR PASSWORD?" link page.
    public void TC4passwordLinkPage(){
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
    }
    @AfterMethod // close driver
    public void closeDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
