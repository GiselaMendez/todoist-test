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
 * Created by Carlos Gonzales on 5/25/2015.
 */
public class AccountSettings {

    private WebDriver driver;
    private WebDriverWait wait;
    private By frameSettingsName = By.name("GB_frame");
    private By frameSettingsId = By.id("GB_frame");

    @FindBy(xpath = "//dt[contains(.,'Full name')]/following::dd/span")
    private WebElement fullNameTxt;

    @FindBy(xpath = "//dt[contains(.,'Full name')]/following::dd/a")
    private WebElement fullNameEditLnk;

    @FindBy(xpath = "//dt[contains(.,'Full name')]/following::dd/form/input")
    private WebElement fullNameTextBox;

    @FindBy(xpath = "//dt[contains(.,'Full name')]/following::dd/form/a[contains(., 'save')]")
    private WebElement saveFullNameLnk;

    @FindBy(xpath = "//dt[contains(.,'Email')]/following::dd/span")
    private WebElement emailTxt;

    public AccountSettings(WebDriver driver) {
        this.driver = driver;
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getFullName() {
        String fullName = "";
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
            fullName = fullNameTxt.getText();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
        return fullName;
    }

    public void clickEditFullName() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
            fullNameEditLnk.click();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void setFullName(String fullName) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
            fullNameTextBox.clear();
            fullNameTextBox.sendKeys(fullName);
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void clickSaveFullName() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
            saveFullNameLnk.click();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getEmail() {
        String email = "";
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
            email = emailTxt.getText();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
        return email;
    }
}
