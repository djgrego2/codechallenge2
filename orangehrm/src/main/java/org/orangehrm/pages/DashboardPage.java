package org.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class DashboardPage {

    public WebDriver driver;

    //PIM MENU OPTION
    By PIM_Menu_Option = By.id("menu_pim_viewPimModule");
    By Add_New_Employee_Menu_Option = By.id("menu_pim_addEmployee");

    // ADMIN MENU OPTION
    By Admin_Menu_Option = By.id("menu_admin_viewAdminModule");
    By User_Management_Menu_Option = By.id("menu_admin_UserManagement");
    By Users_Menu_Option = By.id("menu_admin_viewSystemUsers");

    //HELP OPTION
    By Help_Option = By.xpath("//*[@id=\"branding\"]/a[3]/span");


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public PimPage GoToAddNewEmployee(){
        driver.findElement(PIM_Menu_Option).click();
        driver.findElement(Add_New_Employee_Menu_Option).click();
        PimPage pimPage = new PimPage(driver);

        return  pimPage;
    }

    public AdminPage GoToUsers(){
        driver.findElement(Admin_Menu_Option).click();
        driver.findElement(User_Management_Menu_Option).click();
        driver.findElement(Users_Menu_Option).click();
        AdminPage adminPage = new AdminPage(driver);

        return  adminPage;
    }

    public HelpPage GoToHelpPage(){
        String parentWindows = driver.getWindowHandle();
        driver.findElement(Help_Option).click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String child:allWindows){
            if (!parentWindows.equalsIgnoreCase(child)){
                driver.switchTo().window(child);
            }
        }
        HelpPage helpPage = new HelpPage(driver);
        return  helpPage;
    }
}
