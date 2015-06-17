package com.selenium.course.testng;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.selenium.course.pages.*;

public class EditFullName {
    private HomePage homePage;
    private MainApp mainApp;
    private AccountSettings accountSettings;
    private TodoistSettings todoistSettings;
    String fullName;

    @BeforeMethod(groups = "Acceptance")
    public void setUp() {
        homePage = new HomePage();
        mainApp = homePage.loginAsPrimaryUser();
    }

    @Test(groups = "Acceptance")
    public void testUntitled() {
        String newFullName = "sdasdsadasd";

        TopBar topBar = mainApp.goToTopBar();
        topBar.clickSettingsIcon();
        todoistSettings = topBar.selectTodoistSettingsOptions();

        accountSettings = todoistSettings.clickAccountTab();
        fullName = accountSettings.getFullName();
        accountSettings.clickEditFullName();
        accountSettings.setFullName(newFullName);
        accountSettings.clickSaveFullName();

        Assert.assertEquals(accountSettings.getFullName(), newFullName,
                "Full Name should be edited");
    }

    @AfterMethod(groups = "Acceptance")
    public void tearDown() {
        // Restore
        accountSettings.clickEditFullName();
        accountSettings.setFullName(fullName);
        accountSettings.clickSaveFullName();
        todoistSettings.clickCloseBtn();
    }
}