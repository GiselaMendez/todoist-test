package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Carlos Gonzales on 5/22/2015.
 */
public class LoginForm {

    private WebDriver driver;
    private WebDriverWait wait;

    String frameID = "GB_frame";
    By frameClass = By.className("GB_frame");

    @FindBy(id = "email")
    @CacheLookup
    WebElement emailTxt;

    @FindBy(id = "password")
    @CacheLookup
    WebElement passwordTxt;

    @FindBy(linkText = "Login")
    @CacheLookup
    WebElement loginBtn;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
        try {
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameClass));
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameID));
            wait.until(ExpectedConditions.visibilityOf(passwordTxt));
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void setEmailTxt(String email) {
        try {
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameClass));
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameID));
            wait.until(ExpectedConditions.visibilityOf(emailTxt));
            emailTxt.clear();
            emailTxt.sendKeys(email);
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void setPasswordTxt(String password) {
        try {
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameClass));
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameID));
            passwordTxt.clear();
            passwordTxt.sendKeys(password);
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public MainApp clickLoginBtn() {
        try {
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameClass));
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frameID));
            loginBtn.click();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
        return new MainApp(driver);
    }

    public MainApp loginAs(String email, String password) {
        setEmailTxt(email);
        setPasswordTxt(password);
        return clickLoginBtn();
    }
}
