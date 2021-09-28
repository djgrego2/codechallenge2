package org.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.orangehrm.utils.Helper;

import java.time.LocalTime;

public class TimePage {

    public WebDriver driver;

    SoftAssert softAssertion = new SoftAssert();

    By TimeMenuOption = By.id("menu_time_viewTimeModule");
    By AttendanceSubmenuOption = By.id("menu_attendance_Attendance");
    By MyRecordsSubmenuOption = By.id("menu_attendance_viewMyAttendanceRecord");
    By PunchOption = By.id("menu_attendance_punchIn");
    By AttendanceTime = By.id("attendance_time");
    By AttendanceDate = By.id("attendance_date");
    By AttendanceNote = By.id("attendance_note");
    By PunchButton = By.id("btnPunch");
    By TimeErrorMessage = By.id("timeErrorHolder");

    By PunchInRecord = By.xpath("//*[@id=\"employeeRecordsForm\"]/table/tbody/tr[1]/td[2]");
    By PunchOutRecord = By.xpath("//*[@id=\"employeeRecordsForm\"]/table/tbody/tr[1]/td[4]");

    By SaveNewUserButton = By.id("btnSave");


    public TimePage(WebDriver driver) {
        this.driver = driver;
    }

    public void GoToPunchSystem(){
        driver.findElement(TimeMenuOption).click();
        driver.findElement(AttendanceSubmenuOption).click();
        driver.findElement(PunchOption).click();
    }

    public String GetActualTimeFromPunchSystem(){

        String actualTime = driver.findElement(AttendanceTime).getAttribute("value");

        return actualTime;
    }

    public void punchIn(String newTime){

        driver.findElement(AttendanceTime).clear();
        driver.findElement(AttendanceTime).sendKeys(newTime);
        driver.findElement(AttendanceNote).sendKeys("PUNCH IN EXAMPLE AUTOMATION TEST");
        driver.findElement(PunchButton).click();
    }

    public void punchOut(String newTime){

        driver.findElement(AttendanceTime).clear();
        driver.findElement(AttendanceTime).sendKeys(newTime);
        driver.findElement(AttendanceNote).sendKeys("PUNCH OUT EXAMPLE AUTOMATION TEST");
        driver.findElement(PunchButton).click();

        Boolean failMessage = driver.findElement(TimeErrorMessage).isDisplayed();
        softAssertion.assertTrue(failMessage);
    }

    public void validateInOutPunchTime(String date, String punchInActualSet, String punchOutActualSet){

        driver.findElement(TimeMenuOption).click();
        driver.findElement(AttendanceSubmenuOption).click();
        driver.findElement(MyRecordsSubmenuOption).click();
        driver.findElement(AttendanceDate).clear();
        driver.findElement(AttendanceDate).sendKeys(date);
        driver.findElement(AttendanceDate).sendKeys(Keys.ENTER);

        String puchIn = driver.findElement(PunchInRecord).getText();
        String[] puchIn_arrays = puchIn.split(" ");
        String PunchInTime = puchIn_arrays[1];

        String puchOut = driver.findElement(PunchOutRecord).getText();
        String[] puchOut_arrays = puchOut.split(" ");
        String PunchOutTime = puchOut_arrays[1];

        Assert.assertEquals(PunchInTime.substring(0,5),punchInActualSet);
        Assert.assertEquals(PunchOutTime.substring(0,5),punchOutActualSet);
    }


}
