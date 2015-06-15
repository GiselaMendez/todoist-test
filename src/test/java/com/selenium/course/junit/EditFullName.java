package com.selenium.course.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.selenium.course.pages.*;

public class EditFullName {
    private HomePage homePage;
    private MainApp mainApp;
    private AccountSettings accountSettings;
    private TodoistSettings todoistSettings;
    String fullName;

    @Before
    public void setUp() {
        homePage = new HomePage();
        mainApp = homePage.loginAsPrimaryUser();
    }

    @Test
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

        Assert.assertEquals("Full Name should be edited", newFullName,
                accountSettings.getFullName());
    }

    @After
    public void tearDown() {
        // Restore
        accountSettings.clickEditFullName();
        accountSettings.setFullName(fullName);
        accountSettings.clickSaveFullName();
        todoistSettings.clickCloseBtn();
    }
}