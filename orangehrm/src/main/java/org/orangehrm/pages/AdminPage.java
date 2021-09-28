package org.orangehrm.pages;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {

    public WebDriver driver;

    By AddNewUserButton = By.id("btnAdd");
    By NewEmployeeNameInput = By.id("systemUser_employeeName_empName");
    By NewUsername = By.id("systemUser_userName");
    By NewPassword = By.id("systemUser_password");
    By NewPasswordConfirm = By.id("systemUser_confirmPassword");
    By SaveNewUserButton = By.id("btnSave");

    By SearchSystemUsersInput = By.id("searchSystemUser_userName");
    By SearchSystemUsersButton = By.id("searchBtn");
    By DeleteSystemUsersButton = By.id("btnDelete");
    By SystemUsersCheckBox = By.name("chkSelectRow[]");
    By DeleteSystemUsersConfirmationButton = By.id("dialogDeleteBtn");
    By NoRecordFoundMessage = By.id("//*[@id=\"resultTable\"]/tbody/tr/td");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void AddNewUser(String employeeName, String username, String password) throws InterruptedException {

        driver.findElement(AddNewUserButton).click();
        driver.findElement(NewEmployeeNameInput).sendKeys(employeeName);
        driver.findElement(NewEmployeeNameInput).sendKeys(Keys.ENTER);
        driver.findElement(NewUsername).sendKeys(username);
        driver.findElement(NewPassword).sendKeys(password);
        driver.findElement(NewPasswordConfirm).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(SaveNewUserButton).click();
        Thread.sleep(1000);
    }

    public void DeleteUser(String UserName){
        driver.findElement(SearchSystemUsersInput).sendKeys(UserName);
        driver.findElement(SearchSystemUsersButton).click();
        driver.findElement(SystemUsersCheckBox).click();
        driver.findElement(DeleteSystemUsersButton).click();
        driver.findElement(DeleteSystemUsersConfirmationButton).click();
    }

    public void VerifyIfUserExist(String UserName) throws InterruptedException {
        driver.findElement(SearchSystemUsersInput).sendKeys(UserName);
        driver.findElement(SearchSystemUsersButton).click();
        Thread.sleep(2000);

        Boolean noRecordFoundMessage = driver.findElement(NoRecordFoundMessage).isDisplayed();
        System.out.println("NO RECORD FOUND MESSAGE: " + noRecordFoundMessage);
        Assert.assertTrue(noRecordFoundMessage);
    }
}
