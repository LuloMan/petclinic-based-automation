package org.springframework.samples.petclinic.UITesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.*;

@Epic("PetClinic UI Tests")
@Feature("Visits")
public class VisitsUITest extends BaseUITest{//visit no funciona en el entorno de pruebas


    @Story("Visit Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void createVisit() throws InterruptedException {
        var visitPage = homePage.clickVisitsPage();
        int contador = visitPage.visitList().size();
        visitPage.setVisit( 4, 12, 2016, "Cuidado con el perro", "2");
        visitPage.clickSaveButton();

        Thread.sleep(2000);
        int contadorFinal = visitPage.visitList().size();
        Assert.assertTrue(contadorFinal>contador);
    }

    @Story("Visit Invalid Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void createVisitInvalid() {
        var visitPage = homePage.clickVisitsPage();
        int contador = visitPage.visitList().size();
        visitPage.setVisit( 4, 12, 2016, "Cuidado con el perro", "99");
        visitPage.clickSaveButton();

        int contadorFinal = visitPage.visitList().size();
        Assert.assertTrue(contadorFinal==contador);
    }

    @Story("Visit Update")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void updateVisit() throws InterruptedException {
        var visitPage = homePage.clickVisitsPage();
        visitPage.clickEditButton(11);
        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame);
        driver.findElement(By.cssSelector("body[contenteditable='true']")).clear();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//*[@id=\"visit-form\"]/input[3]")).clear();
        visitPage.setVisit(4, 12, 2016,"Updated", "3");
        visitPage.clickSaveButton();

        Thread.sleep(2000);
        String newId = driver.findElement(
        By.xpath("//tr[td[normalize-space(.)=3]]/td[4]")).getDomProperty("textContent").trim();
        Assert.assertEquals(newId, "3");
    }

    @Story("Visit Delete")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void deleteVisit() {
       var visitPage = homePage.clickVisitsPage();
       int contador = visitPage.visitList().size();
       visitPage.clickDeleteButton(11);

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       wait.until(driver -> visitPage.visitList().size() > 0);
       driver.navigate().refresh();//como la lista se actualiza al reiniciar sesion, hago esto
       int contadorFinal = visitPage.visitList().size();
       Assert.assertTrue(contadorFinal<contador);
    }

}
