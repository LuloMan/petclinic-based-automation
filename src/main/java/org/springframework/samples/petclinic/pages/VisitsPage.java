package org.springframework.samples.petclinic.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VisitsPage {

    private WebDriver driver;

    public VisitsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> visitList(){
        List<WebElement> lista = driver.findElements(By.xpath("//tr/td[2]"));
        return lista;
    }

    public void clickSaveButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By saveButton = By.xpath("//form[@id='visit-form']//button[@type='submit']");

        // Espera hasta que el botón esté visible y habilitado
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        button.click();
    }

     public void clickEditButton(Integer id){
        driver.findElement(By.xpath("//tr[td[normalize-space(text())='" + id + "']]/td[5]//button[contains(text(),'Edit')]")).click();
    }

    public void clickDeleteButton(Integer id){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By deleteButton = By.xpath("//tr[td[normalize-space(text())='" + id + "']]/td[5]//button[contains(text(),'Delete')]");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        button.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void setVisit(Integer day, Integer month, Integer year, String description, String petId){
        WebElement birth = driver.findElement(By.cssSelector("input[type='date'][name='date']"));
        LocalDate date = LocalDate.of(year, month, day);
        String fecha = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        WebElement iFrame = driver.findElement(By.tagName("iframe"));

        birth.sendKeys(fecha);
        driver.switchTo().frame(iFrame);
        driver.findElement(By.cssSelector("body[contenteditable='true']")).sendKeys(description);
        driver.switchTo().defaultContent();
        driver.findElement(By.name("petId")).sendKeys(petId);
    }

    public String getFirstVisitDescription() {
        return driver.findElement(By.xpath("/html/body/main/section[5]/div/table/tbody/tr/td[3]")).getText();
    }
}
