package org.springframework.samples.petclinic.UITesting;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.samples.petclinic.pages.HomePage;
import org.testng.ITestResult;
import com.google.common.io.Files;

import io.restassured.RestAssured;

public class BaseUITest {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:9966/petclinic/index.html");
        RestAssured.basePath = "/petclinic/api";
        driver.findElement(By.id("login-username")).sendKeys("user");
        driver.findElement(By.id("login-password")).sendKeys("password");
        driver.findElement(By.id("login-btn")).click();
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        var camera = (TakesScreenshot)driver;
        if (result.getStatus() == ITestResult.FAILURE) {
            try{
                File screenshot = camera.getScreenshotAs(OutputType.FILE);
                System.out.println("Screenshot taken: " + screenshot.getAbsolutePath());
                Files.move(screenshot, new File("target/screenshot-results/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}