package org.springframework.samples.petclinic.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OwnerPage {

    private WebDriver driver;

    public OwnerPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> ownersList(){
        List<WebElement> names = driver.findElements(By.xpath("//tr/td[2]"));
        return names;
    }
    
    public void clickSaveButton(){
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public void clickEditButton (Integer id){
        driver.findElement(By.xpath("//tr[td[text()='" + id + "']]//button[contains(@class,'edit')]")).click();
    }
    
    public void clickDeleteButton (Integer id){
        driver.findElement(By.xpath("//tr[td[text()='" + id + "']]//button[contains(text(),'Delete')]")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

     public void setOwner (String firstName, String lastName, String address, String city, String telephone){
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.name("address")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("telephone")).sendKeys(telephone);
    }
}
