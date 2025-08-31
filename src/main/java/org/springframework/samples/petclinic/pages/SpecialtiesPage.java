package org.springframework.samples.petclinic.pages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpecialtiesPage {

    private WebDriver driver;

    public SpecialtiesPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> specialtyList(){
        List<WebElement> lista = driver.findElements(By.xpath("//tr/td[2]"));
        return lista;
    }

    public void uploadCSV (){
        Path path = Paths.get("src/test/resources/datasets/specialties.csv").toAbsolutePath();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(path.toString());
    }

    public void CSVbutton(){
        driver.findElement(By.xpath("//*[@id=\"specialty-upload-btn\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar a que aparezca el modal
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-modal")));

        // Esperar a que el botón esté clickeable
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("error-modal-close")));
        closeButton.click();
    }
    
    public void clickSaveButton (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By saveButton = By.xpath("//*[@id=\"specialty-form\"]/button[1]");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        button.click();
    }
    
    public void clickEditButton (String name){
        driver.findElement(By.xpath("//tr[td[normalize-space(text())='" + name + "']]//button[contains(text(),'Edit')]")).click();
    }

    public void clickDeleteButton (String name){
        driver.findElement(By.xpath("//tr[td[normalize-space(text())='" + name + "']]//button[contains(text(),'Delete')]")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void setSpecialty(String name){
        driver.findElement(By.xpath("//*[@id=\"specialty-form\"]/input[2]")).sendKeys(name);
    }

    public String getNameSpecialty() {
        return driver.findElement(By.xpath("/html/body/main/section[7]/div[2]/table/tbody/tr/td[2]")).getText();
    }
}
