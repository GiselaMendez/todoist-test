package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by carlos_gonzales on 27-05-15.
 */
public class ProjectEditor {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Add Task")
    @CacheLookup
    private WebElement addTaskLink;

    @FindBy(xpath = "//div[@class='AmiMenu' and " +
            "not(contains(@style, 'none'))]/" +
            "descendant::div[contains(.,'Delete task')]")
    private WebElement deleteTaskOption;

    public ProjectEditor(WebDriver driver) {
        this.driver = driver;
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public AddTask clickAddTaskLnk() {
        addTaskLink.click();
        return new AddTask(driver);
    }

    public Boolean isTaskAdded(String taskName) {
        By taskXpath = By.xpath("//tr[contains(.,'" + taskName + "')]");
        return isElementPresent(driver.findElement(taskXpath));
    }

    public void clickTaskMenu(String taskName) {
        WebElement taskElement;
        taskElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//tr[contains(.,'" + taskName + "')]")));

        Action rightClick = new Actions(driver)
                .contextClick(taskElement).build();
        rightClick.perform();
    }

    public void clickDeleteTask() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteTaskOption));
        deleteTaskOption.click();
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
