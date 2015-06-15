package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import com.selenium.course.framework.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Carlos Gonzales on 5/22/2015.
 */
public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Login")
    @CacheLookup
    WebElement loginBtn;

    public HomePage() {
        driver = DriverManager.getInstance().getDriver();
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public LoginForm clickLoginBtn() {
        wait.until(ExpectedConditions
                .elementToBeClickable(loginBtn));
        loginBtn.click();
        return new LoginForm(driver);
    }

    public MainApp loginAs(String email, String password) {
        MainApp mainApp;
        try {
            mainApp = new MainApp(driver);
            TopBar topBar = mainApp.goToTopBar();
            if (topBar.isSettingIconDisplayed()) {
                if (!topBar.isLoggedAsEmail(email)){
                    topBar.logoutUser();
                    LoginForm loginForm = clickLoginBtn();
                    loginForm.loginAs(email, password);
                }
            }

        } catch (WebDriverException e) {
            LoginForm loginForm = clickLoginBtn();
            mainApp = loginForm.loginAs(email, password);
        }
        return mainApp;
    }

    public MainApp loginAsPrimaryUser() {
        return loginAs(Environment.getInstance().getPrimaryUser(),
                Environment.getInstance().getPrimaryPassword());
    }

}
