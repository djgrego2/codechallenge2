package org.orangehrm.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public WebDriver driver;

    By UsernameInputText = By.id("txtUsername");
    By PasswordInputText = By.id("txtPassword");
    By LoginButton = By.id("btnLogin");
    By LoginPanelHeading = By.id("logInPanelHeading");
    By InvalidUsernameOrPasswordMessage = By.id("spanMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void LoginAdmin(String loginUsername, String LoginPassword) {

        Boolean usernameInputText = driver.findElement(UsernameInputText).isDisplayed();
        Boolean passwordInputText = driver.findElement(PasswordInputText).isDisplayed();
        Boolean loginButton = driver.findElement(LoginButton).isDisplayed();
        Boolean loginPanelHeading = driver.findElement(LoginPanelHeading).isDisplayed();

        Assert.assertTrue(usernameInputText);
        Assert.assertTrue(passwordInputText);
        Assert.assertTrue(loginButton);
        Assert.assertTrue(loginPanelHeading);

        driver.findElement(UsernameInputText).sendKeys(loginUsername);
        driver.findElement(PasswordInputText).sendKeys(LoginPassword);
        driver.findElement(LoginButton).click();
    }

    public void VerifyUserExist(String loginUsername, String LoginPassword){
        driver.findElement(UsernameInputText).sendKeys(loginUsername);
        driver.findElement(PasswordInputText).sendKeys(LoginPassword);
        driver.findElement(LoginButton).click();

        Boolean InvalidMessage = driver.findElement(InvalidUsernameOrPasswordMessage).isDisplayed();
        Assert.assertTrue(InvalidMessage);
    }
}
