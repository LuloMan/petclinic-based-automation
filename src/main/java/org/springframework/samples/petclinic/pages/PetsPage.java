package org.springframework.samples.petclinic.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PetsPage {

    private WebDriver driver;

    public PetsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> petList(){
        List<WebElement> lista = driver.findElements(By.xpath("//tr/td[2]"));
        return lista;
    }

    public void clickSaveButton(){
        driver.findElement(By.className("button-primary")).click();
    }

    public void clickEditButton (String name){
        driver.findElement(By.xpath("//tr[td[normalize-space(text())='" + name + "']]//button[contains(text(),'Edit')]")).click();
    }
    
    public void clickDeleteButton (String name){
        driver.findElement(By.xpath("//tr[td[normalize-space(text())='" + name + "']]//button[contains(text(),'Delete')]")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void setPet (String petName, Integer day, Integer month, Integer year, String ownerId, String petType){
        WebElement birth = driver.findElement(By.cssSelector("input[type='date'][name='birthDate']"));
        LocalDate date = LocalDate.of(year, month, day);
        String fecha = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        WebElement petTypeSelect = driver.findElement(By.id("pet-type-select"));
        Select select = new Select(petTypeSelect);

        driver.findElement(By.name("name")).sendKeys(petName);
        birth.sendKeys(fecha);
        driver.findElement(By.name("ownerId")).sendKeys(ownerId);
        select.selectByVisibleText(petType);
    }
}
