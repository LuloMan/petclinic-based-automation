package org.springframework.samples.petclinic.UITesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.qameta.allure.*;
import io.restassured.RestAssured;

@Epic("PetClinic UI Tests")
@Feature("User")
public class UserUITest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:9966/petclinic/index.html");
        RestAssured.basePath = "/petclinic/api";
    }

    @Story("User Login Exitoso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void login(){
        driver.findElement(By.id("login-username")).sendKeys("user");
        driver.findElement(By.id("login-password")).sendKeys("password");
        driver.findElement(By.id("login-btn")).click();
        WebElement inicio = driver.findElement(By.id("dashboard"));
        assertTrue(inicio.isDisplayed());
    }

    @Story("User Login Invalido")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loginInvalido(){
        driver.findElement(By.id("login-username")).sendKeys("userNO");
        driver.findElement(By.id("login-password")).sendKeys("passwordNO");
        driver.findElement(By.id("login-btn")).click();
        String mensaje = driver.findElement(By.xpath("//*[@id=\"login-error\"]")).getText().trim();
        assertEquals(mensaje, "Usuario o contraseña incorrectos");
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
