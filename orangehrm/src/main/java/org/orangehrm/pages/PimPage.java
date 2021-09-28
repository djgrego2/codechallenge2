package org.orangehrm.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class PimPage {

    Properties prop = new Properties();
    //int MedTimeout = Integer.parseInt(prop.getProperty("MED_TIMEOUT"));
    public WebDriver driver;

    // ADD NEW SECTION
    By FirstName = By.id("firstName");
    By LastName = By.id("lastName");
    By SaveButton = By.id("btnSave");

    By SavedFirstName = By.id("personal_txtEmpFirstName");
    By SavedLastName = By.id("personal_txtEmpLastName");

    //EDIT SECTION
    By MaleGender = By.id("personal_optGender_1");
    By FemaleGender = By.id("personal_optGender_2");
    By Nationality = By.id("personal_cmbNation");
    By BirthDay = By.id("personal_DOB");
    By DatePickerIcon = By.xpath("//*[@id=\"frmEmpPersonalDetails\"]/fieldset/ol[3]/li[4]/img");
    By Smoker = By.id("personal_chkSmokeFlag");

    String GenderCheck = "";
    String SmokerStatus = "";

    //EMPLOYEE LIST
    By EmployeeListMenuOption = By.id("menu_pim_viewEmployeeList");
    By EmployeeName = By.name("empsearch[employee_name][empName]");
    By SearchEmployeeByName = By.id("searchBtn");

    //SIDE PANEL SECTION: REPORT TO OPTION

    By ReportTo = By.partialLinkText("Report-to");
    By AddSupervisorButton = By.id("btnAddSupervisorDetail");
    By SupervisorNameInputText = By.id("reportto_supervisorName_empName");
    By ReportingMethod = By.id("reportto_reportingMethodType");
    By SaveReportButton = By.id("btnSaveReportTo");
    By CanceltButton = By.id("btnCancel");

    //SIDE PANEL SECTION: QUALIFICATIONS

    By Qualifications = By.partialLinkText("Qualifications");

    //QUALIFICATIONS - WORKEXPERIENCE

    By CompanyInput = By.id("experience_employer");
    By JobTitleInput = By.id("experience_jobtitle");
    By AddWorkExperience = By.id("addWorkExperience");
    By WorkExpSaveButton = By.id("btnWorkExpSave");
    By WorkExpCancelButton = By.id("btnWorkExpCancel");

    //QUALIFICATIONS - EDUCATION

    By SelectEducationLevel = By.id("education_code");
    By AddEducationButton = By.id("addEducation");
    By EducationSaveButton = By.id("btnEducationSave");
    By EducationCancelButton = By.id("btnEducationCancel");

    //QUALIFICATIONS - SKILL

    By SelectSkill = By.id("skill_code");
    By AddSkillButton = By.id("addSkill");
    By SkillSaveButton = By.id("btnSkillSave");

    //QUALIFICATIONS - LENGUAGE

    By SelectLanguage = By.id("language_code");
    By SelectFluency = By.id("language_lang_type");
    By SelectCompetency = By.id("language_competency");
    By AddLanguageButton = By.id("addLanguage");
    By LanguageSaveButton = By.id("btnLanguageSave");

    //QUALIFICATIONS - LICENSE

    By SelectLicense = By.id("license_code");
    By AddLicenseButton = By.id("addLicense");
    By LicenseSaveButton = By.id("btnLicenseSave");

    //QUALIFICATIONS - ATTACHMENTS

    By AddAttachmentButton = By.id("btnAddAttachment");
    By SelectFile = By.id("ufile");
    By CommentsField = By.id("txtAttDesc");
    By UploadButton = By.id("btnSaveAttachment");



    public PimPage(WebDriver driver) {
        this.driver = driver;
    }

    public String AddNewEmployee(String firstName, String lastName){
        //WebDriverWait wait = new WebDriverWait(driver, MedTimeout);
        driver.findElement(FirstName).sendKeys(firstName);
        driver.findElement(LastName).sendKeys(lastName);
        driver.findElement(SaveButton).click();

        String ActualFirstNameText = driver.findElement(SavedFirstName).getAttribute("value");
        String ActualLastNameText = driver.findElement(SavedLastName).getAttribute("value");

        Assert.assertEquals(firstName, ActualFirstNameText);
        Assert.assertEquals(lastName, ActualLastNameText);

        return firstName;
    }

    public void EditEmployee(Boolean gender, String nationality, String birthDay, Boolean smoker){

        driver.findElement(SaveButton).click();

        Select nationalityOptions = new Select(driver.findElement(Nationality));
        Boolean smokerIsChecked = driver.findElement(Smoker).isSelected();

        if (gender.equals(true)){
            driver.findElement(MaleGender).click();
            GenderCheck = "1";
        }else{
            driver.findElement(FemaleGender).click();
            GenderCheck = "2";
        }

        nationalityOptions.selectByVisibleText(nationality);
        driver.findElement(BirthDay).clear();
        driver.findElement(BirthDay).sendKeys(birthDay);
        driver.findElement(DatePickerIcon).click();

        if (smokerIsChecked.equals(false) && smoker.equals(true)){
            driver.findElement(Smoker).click();
            SmokerStatus = "1";
        }else{
            System.out.println("ISNT SMOKER");
            SmokerStatus = "2";
        }

        driver.findElement(SaveButton).click();

        String ActualGender = driver.findElement(MaleGender).getAttribute("value");
        String ActualBirthDay = driver.findElement(BirthDay).getAttribute("value");
        String ActualSmokerStatus = driver.findElement(Smoker).getAttribute("value");

        String ActualNationality = driver.findElement(Nationality).getAttribute("value");
        System.out.println(ActualNationality);

        Assert.assertEquals(GenderCheck, ActualGender);
        Assert.assertEquals(birthDay, ActualBirthDay);
        Assert.assertEquals(SmokerStatus, ActualSmokerStatus);
        //Assert.assertEquals(gender, ActualGender);
    }

    public String AddSupervisor(String supervisorName, String reportingMethod){

        driver.findElement(ReportTo).click();
        driver.findElement(AddSupervisorButton).click();
        Select reportingMethodOptions = new Select(driver.findElement(ReportingMethod));
        driver.findElement(SupervisorNameInputText).sendKeys(supervisorName);
        reportingMethodOptions.selectByVisibleText(reportingMethod);
        driver.findElement(SaveReportButton).click();

        return supervisorName;
    }

    public void AddWorkExperience(){

        driver.findElement(AddWorkExperience).click();
        driver.findElement(CompanyInput).sendKeys("COMPANY NAME AUTOMATION TEST");
        driver.findElement(JobTitleInput).sendKeys("JOBTITLE AUTOMATION TEST");
        driver.findElement(WorkExpSaveButton).click();
    }

    public void AddEducation(){

        driver.findElement(AddEducationButton).click();
        Select EducationLevelOptions = new Select(driver.findElement(SelectEducationLevel));
        EducationLevelOptions.selectByVisibleText("Master's Degree");
        driver.findElement(EducationSaveButton).click();
    }

    public void AddSkills(){

        driver.findElement(AddSkillButton).click();
        Select skillOptions = new Select(driver.findElement(SelectSkill));
        skillOptions.selectByVisibleText("Java");
        driver.findElement(SkillSaveButton).click();
    }

    public void AddLenguage(){

        driver.findElement(AddLanguageButton).click();
        Select languageOptions = new Select(driver.findElement(SelectLanguage));
        Select fluencyOptions = new Select(driver.findElement(SelectFluency));
        Select competencyOptions = new Select(driver.findElement(SelectCompetency));

        languageOptions.selectByVisibleText("Chinese");
        fluencyOptions.selectByVisibleText("Reading");
        competencyOptions.selectByVisibleText("Good");

        driver.findElement(LanguageSaveButton).click();
    }

    public void AddLicense(){

        driver.findElement(AddLicenseButton).click();
        Select licenseOptions = new Select(driver.findElement(SelectLicense));
        licenseOptions.selectByVisibleText("PMI Agile Certified Practitioner (PMI-ACP)");
        driver.findElement(LicenseSaveButton).click();
    }

    public void AddQualificationsCategory(){

        driver.findElement(Qualifications).click();

        AddWorkExperience();
        AddEducation();
        AddSkills();
        AddLenguage();
        AddLicense();
    }

    public void SearchEmployee(String eName, String sName){

        driver.findElement(EmployeeListMenuOption).click();
        driver.findElement(EmployeeName).sendKeys(eName);
        driver.findElement(EmployeeName).sendKeys(Keys.ENTER);
        driver.findElement(SearchEmployeeByName).click();

        driver.findElement(EmployeeName).clear();
        driver.findElement(EmployeeName).sendKeys(sName);
        driver.findElement(SearchEmployeeByName).click();
    }
}
