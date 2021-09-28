package org.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeUserGuidePage {

    public WebDriver driver;

    By EmployeeUserGuideOption = By.partialLinkText("Employee User Guide");


    public EmployeeUserGuidePage(WebDriver driver) {
        this.driver = driver;
    }

    public void getTitle(){
        System.out.println(driver.getTitle());
    }
}
