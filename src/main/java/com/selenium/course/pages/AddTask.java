package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by carlos_gonzales on 27-05-15.
 */
public class AddTask {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "ta")
    @CacheLookup
    private WebElement taskNameTxt;

    @FindBy(xpath = "//img[contains(@class,'cmp_priority') " +
            "and contains(@class, 'form_action_icon menu_icon')]")
    @CacheLookup
    private WebElement priorityIcon;

    @FindBy(css = "a.amibutton.amibutton_red.submit_btn")
    @CacheLookup
    private WebElement addTaskBtn;

    @FindBy(css = "div.AmiMenu.priority_menu")
    private WebElement priorityMenu;

    public AddTask(WebDriver driver) {
        this.driver = driver;
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public AddTask setTaskNameTxt(String newNameTask){
        taskNameTxt.clear();
        taskNameTxt.sendKeys(newNameTask);
        return this;
    }

    public AddTask selectPriority(int priority){
        priorityIcon.click();
        priorityMenu.findElement(By.cssSelector("img.cmp_priority" + priority)).click();
        return this;

        //Another implementation using xpath
//        driver.findElement(
//                By.xpath("//div[@class='AmiMenu priority_menu']/descendant::img[@class='cmp_priority" + priority + "']")).click();
    }

    public AddTask selectProject(String projectName) {
        // implementation to select a project
        return this;
    }

    public AddTask setDate(String date) {
        // implementation to set a date
        return this;
    }

    public ProjectEditor clickAddTaskBtn(){
        addTaskBtn.click();
        return new ProjectEditor(driver);
    }
}



