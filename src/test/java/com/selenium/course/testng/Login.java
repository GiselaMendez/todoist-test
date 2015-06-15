package com.selenium.course.testng;

import com.selenium.course.framework.Environment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.selenium.course.pages.*;

public class Login {
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
         homePage = new HomePage();
    }

    @Test
    public void testUntitled() {
        String email = Environment.getInstance().getPrimaryUser();
        String password = Environment.getInstance().getPrimaryPassword();

        LoginForm loginForm = homePage.clickLoginBtn();
        loginForm.setEmailTxt(email);
        loginForm.setPasswordTxt(password);

        MainApp mainApp = loginForm.clickLoginBtn();
        TopBar topBar = mainApp.goToTopBar();
        topBar.clickSettingsIcon();

        TodoistSettings todoistSettings = topBar.selectTodoistSettingsOptions();

        AccountSettings accountSettings = todoistSettings.clickAccountTab();
        Assert.assertEquals(accountSettings.getEmail(), email,
                "The correct email should be displayed");

        todoistSettings.clickCloseBtn();
    }
}