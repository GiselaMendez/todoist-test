package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Carlos Gonzales on 5/22/2015.
 */
public class TopBar {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "img.cmp_add_task.icon.fixed_pos")
    @CacheLookup
    private WebElement addTaskIcon;

    @FindBy(css = "img.cmp_gear.icon.fixed_pos")
    @CacheLookup
    private WebElement settingsIcon;

    @FindBy(xpath = "//td[contains(.,'Todoist Settings')]")
    @CacheLookup
    private WebElement todoistSettingsOption;

    @FindBy(xpath = "//td[contains(.,'Logout')]")
    @CacheLookup
    private WebElement logoutOption;

    public TopBar(WebDriver driver) {
        this.driver = driver;
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
        try {
            wait.withTimeout(3, TimeUnit.SECONDS)
                    .until(ExpectedConditions.visibilityOf(settingsIcon));
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            wait.withTimeout(15, TimeUnit.SECONDS);
        }
    }

    public AddTask clickAddTaskIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(addTaskIcon));
        addTaskIcon.click();
        return new AddTask(driver);
    }

    public void clickSettingsIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsIcon));
        settingsIcon.click();
    }

    public TodoistSettings selectTodoistSettingsOptions() {
        todoistSettingsOption.click();
        return new TodoistSettings(driver);
    }

    public void logoutUser(){
        settingsIcon.click();
        logoutOption.click();
    }

    public boolean isSettingIconDisplayed() {
        return isElementPresent(settingsIcon);
    }

    public boolean isLoggedAsEmail(String email){
        boolean isLoggerUser = false;
        clickSettingsIcon();
        TodoistSettings todoistSettings = selectTodoistSettingsOptions();
        AccountSettings accountSettings = todoistSettings.clickAccountTab();
        String emailLogged = accountSettings.getEmail();
        todoistSettings.clickCloseBtn();
        if (emailLogged.equals(email)){
            isLoggerUser = true;
        }
        return isLoggerUser;
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            webElement.getTagName();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

}
