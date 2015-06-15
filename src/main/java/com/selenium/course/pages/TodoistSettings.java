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
public class TodoistSettings {

    private WebDriver driver;
    private WebDriverWait wait;
    private By frameSettingsName = By.name("GB_frame");
    private By frameSettingsId = By.id("GB_frame");

    @FindBy(linkText = "Account")
    @CacheLookup
    private WebElement accountTab;

    @FindBy(css = "img.cmp_small_close")
    @CacheLookup
    private WebElement closeBtn;

    public TodoistSettings(WebDriver driver) {
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

    public AccountSettings clickAccountTab() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsName));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameSettingsId));
            accountTab.click();
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
        return new AccountSettings(driver);
    }

    public void clickCloseBtn() {
        closeBtn.click();
    }
}
