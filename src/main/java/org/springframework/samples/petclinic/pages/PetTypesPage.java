package org.springframework.samples.petclinic.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class PetTypesPage {

    private WebDriver driver;

    public PetTypesPage(WebDriver driver) {
        this.driver = driver;
    }

     public List<WebElement> petTypesList(){
        List<WebElement> list = driver.findElements(By.xpath("//tr/td[2]"));
        return list;
    }

    public void clickSaveButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By saveButton = By.xpath("//form[@id='pettype-form']//button[@type='submit']");

        // Espera hasta que el botón esté visible y habilitado
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        button.click();
    }

    public void clickEditButton(String name){
        driver.findElement(By.xpath("//tr[td[normalize-space(text())='" + name + "']]/td[3]//button[contains(text(),'Edit')]")).click();
    }

    public void clickDeleteButton(String id){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By deleteButton = By.xpath("//tr[td[normalize-space(text())='" + id + "']]//button[contains(text(),'Delete')]");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        button.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void setPetTypes(String name){
        driver.findElement(By.xpath("//*[@id='pettype-form']/input[2]")).sendKeys(name);
    }

    public String getNamePetType() {
        return driver.findElement(By.xpath("/html/body/main/section[6]/div/table/tbody/tr/td[2]")).getText();
    }
}
