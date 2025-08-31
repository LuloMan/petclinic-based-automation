package org.springframework.samples.petclinic.UITesting;

import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.samples.petclinic.APITesting.CsvDataProvider;
import org.springframework.samples.petclinic.pages.OwnerPage;
import org.testng.annotations.Test;

import io.qameta.allure.*;

@Epic("PetClinic UI Tests")
@Feature("Owners")
public class OwnersUITest extends BaseUITest {

    @Story("Owner Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "ownersData", dataProviderClass = CsvDataProvider.class)
    public void createOwner(String firstName, String lastName, String address, String city, String telephone) throws InterruptedException {
        var ownerPage = homePage.clickOwnerPage();
        int contador = ownerPage.ownersList().size();
        ownerPage.setOwner(firstName, lastName, address, city, telephone);
        ownerPage.clickSaveButton();

        Thread.sleep(2000);
        int contadorFinal = ownerPage.ownersList().size();
        Assert.assertTrue(contadorFinal>contador);
    }

    @Story("Owner Invalid Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createInvalidOwner() {
        var ownerPage = homePage.clickOwnerPage();
        int contador = ownerPage.ownersList().size();
        ownerPage.setOwner("1234", "lastName", "address", "", "78");
        ownerPage.clickSaveButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td[2]")));
        int contadorFinal = ownerPage.ownersList().size();
        Assert.assertTrue(contadorFinal==contador);
    }

    @Story("Owner Update")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateOwner() throws InterruptedException {
        OwnerPage ownerPage = homePage.clickOwnerPage();
        Integer id = 9;
        ownerPage.clickEditButton(id);
        driver.findElement(By.name("firstName")).clear();
        ownerPage.setOwner("Updated","Updated","calleUpdated","ciudadUpdated","1234567890");
        ownerPage.clickSaveButton();

        Thread.sleep(2000);
        String result = driver.findElement(By.xpath("//*[@id=\"owners-list\"]/table/tbody/tr[" + id + "]/td[2]")).getText();
        Assert.assertEquals(result, "Updated");
    }

    @Story("Owner Delete")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void deleteOwner() throws InterruptedException {
        OwnerPage ownerPage = homePage.clickOwnerPage();
         int contador = ownerPage.ownersList().size();
        ownerPage.clickDeleteButton(10);

        Thread.sleep(2000);
        int contadorFinal = ownerPage.ownersList().size();
        Assert.assertTrue(contadorFinal<contador);
    }
}
