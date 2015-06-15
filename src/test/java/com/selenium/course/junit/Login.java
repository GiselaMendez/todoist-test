package com.selenium.course.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.selenium.course.pages.*;

import static org.junit.Assert.fail;

public class Login {
    private HomePage homePage;

    @Before
    public void setUp() {
         homePage = new HomePage();
    }

    @Test
    public void testUntitled() {
        String email = "carledriss@gmail.com";
        String password = "P@ssw0rd";

        LoginForm loginForm = homePage.clickLoginBtn();
        loginForm.setEmailTxt(email);
        loginForm.setPasswordTxt(password);

        MainApp mainApp = loginForm.clickLoginBtn();
        TopBar topBar = mainApp.goToTopBar();
        topBar.clickSettingsIcon();

        TodoistSettings todoistSettings = topBar.selectTodoistSettingsOptions();

        AccountSettings accountSettings = todoistSettings.clickAccountTab();
        Assert.assertEquals("The correct email should be displayed",
                email, accountSettings.getEmail());

        todoistSettings.clickCloseBtn();
    }

    @After
    public void tearDown() {

    }
}