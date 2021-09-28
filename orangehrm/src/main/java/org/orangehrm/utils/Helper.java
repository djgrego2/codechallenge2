package org.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.*;
import org.joda.time.format.*;

public class Helper {

    public WebDriver driver;

    By WelcomeMenuOption = By.id("welcome");
    By LogOutOption = By.xpath("//*[@id=\"welcome-menu\"]/ul/li[3]/a");

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void logOut()  {
        driver.findElement(WelcomeMenuOption).click();
        driver.findElement(LogOutOption).click();
    }

    public String AddMinutesToTime(String actualTime, int minutesToAdding)  {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime time = formatter.parseLocalTime(actualTime);
        time = time.plusMinutes(minutesToAdding);
        String newTime = time.toString();

        return newTime.substring(0,5);
    }

    public String SubtractMinutesToTime(String actualTime, int minutesToSubtracting)  {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime time = formatter.parseLocalTime(actualTime);
        time = time.minusMinutes(minutesToSubtracting);
        String newTime = time.toString();

        return newTime.substring(0,5);
    }

    public String ActualDate(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println("DATE" + timeStamp);

        return timeStamp;
    }
}
