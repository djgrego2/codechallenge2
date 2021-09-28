package org.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelpPage {

    public WebDriver driver;

    By EmployeeUserGuideOption = By.xpath("/html/body/main/div/section[1]/section/ul/li[2]/a/span[1]");


    public HelpPage(WebDriver driver) {
        this.driver = driver;
    }

    public EmployeeUserGuidePage GoToEmployeeUserGuidePage(){

        driver.findElement(EmployeeUserGuideOption).click();
        EmployeeUserGuidePage employeeUserGuidePage = new EmployeeUserGuidePage(driver);

        return employeeUserGuidePage;
    }
}
