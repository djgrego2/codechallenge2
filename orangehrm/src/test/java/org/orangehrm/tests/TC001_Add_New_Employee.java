package org.orangehrm.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.orangehrm.pages.*;
import org.orangehrm.resources.Initializer;
import org.orangehrm.utils.Helper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC001_Add_New_Employee extends Initializer {

    public static Logger log = LogManager.getLogger(Initializer.class.getName());
    public WebDriver driver;

    @BeforeTest
    public void initializer() throws IOException {
        driver = initializerDriver();
        log.info("Driver Initialized and Navigated LoginPage");
    }

    @Test(priority = 1)
    public void TC_Add_New_Item_Employee_Successfully() {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.LoginAdmin("Admin", "admin123");
        PimPage pimPage = dashboardPage.GoToAddNewEmployee();
        pimPage.AddNewEmployee("Gregorio", "Austin");

        log.info("LOGIN SUCCESSFULLY");

    }

    @Test(priority = 1)
    public void TC_Edit_Employee_Successfully() {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.LoginAdmin("Admin", "admin123");
        PimPage pimPage = dashboardPage.GoToAddNewEmployee();
        String NewEmployee = pimPage.AddNewEmployee("Gregorio", "Austin");
        String firstThreeLettersNewEmployeeName = NewEmployee.substring(0,3);
        pimPage.EditEmployee(true,"Russian","1993-04-06", true);
        String SupervisorName = pimPage.AddSupervisor("John Smith","Direct");
        pimPage.AddQualificationsCategory();
        pimPage.SearchEmployee(firstThreeLettersNewEmployeeName, SupervisorName);

        log.info("LOGIN SUCCESSFULLY");

    }

    @Test(priority = 1)
    public void TC_Search_Employee_Successfully() {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.LoginAdmin("Admin","admin123");
        PimPage pimPage = dashboardPage.GoToAddNewEmployee();
        String NewEmployee = pimPage.AddNewEmployee("Gregorio", "Austin");
        String firstThreeLettersNewEmployeeName = NewEmployee.substring(0,3);
        String SupervisorName = pimPage.AddSupervisor("John Smith","Direct");
        pimPage.SearchEmployee(firstThreeLettersNewEmployeeName, SupervisorName);
        AdminPage adminPage = dashboardPage.GoToUsers();
    }

    @Test(priority = 1)
    public void TC_Add_New_User_Successfully() throws InterruptedException {

        //NEW USER INFO
        String usernameTest = "automation_username011";
        String passwordTest = "automation_password123";

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        TimePage timePage = new TimePage(driver);
        Helper helper = new Helper(driver);

        loginPage.LoginAdmin("Admin", "admin123");
        PimPage pimPage = dashboardPage.GoToAddNewEmployee();
        String NewEmployee = pimPage.AddNewEmployee("Gregorio", "Austin");
        AdminPage adminPage = dashboardPage.GoToUsers();
        adminPage.AddNewUser(NewEmployee,usernameTest, passwordTest);
        helper.logOut();
        loginPage.LoginAdmin(usernameTest,passwordTest);
        timePage.GoToPunchSystem();

        // ADD 5 MINUTES
        String FiveMinuteAddedToTime = helper.AddMinutesToTime(timePage.GetActualTimeFromPunchSystem(), 5);
        timePage.punchIn(FiveMinuteAddedToTime);

        // REST 10 MINUTES
        String TenMinuteSubtractedFromPunchTime = helper.SubtractMinutesToTime(FiveMinuteAddedToTime, 10);
        timePage.punchOut(TenMinuteSubtractedFromPunchTime);

        String TenMinuteAddedToTime = helper.AddMinutesToTime(FiveMinuteAddedToTime, 10);
        timePage.punchOut(TenMinuteAddedToTime);
        System.out.println("PUNCH OUT "+TenMinuteAddedToTime);

        timePage.validateInOutPunchTime(helper.ActualDate(), FiveMinuteAddedToTime, TenMinuteAddedToTime);

        //HelpPage helpPage = dashboardPage.GoToHelpPage();
        //EmployeeUserGuidePage employeeUserGuidePage = helpPage.GoToEmployeeUserGuidePage();
        //employeeUserGuidePage.getTitle();
        helper.logOut();
        loginPage.LoginAdmin("Admin", "admin123");
        dashboardPage.GoToUsers();
        adminPage.DeleteUser(usernameTest);
        adminPage.VerifyIfUserExist(usernameTest);
        //helper.logOut();
        //loginPage.VerifyUserExist(usernameTest,passwordTest);
    }
}
