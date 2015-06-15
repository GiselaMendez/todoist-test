package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Carlos Gonzales on 5/31/2015.
 */
public class DeleteAlert {

    private WebDriver driver;
    private WebDriverWait wait;
    private By deleteAlertContainer = By.cssSelector("div.GB_Window_holder");
    private By okBtn = By.xpath("//span[contains(.,'Ok')]");

    public DeleteAlert(WebDriver driver) {
        this.driver = driver;
        this.wait = DriverManager.getInstance().getWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteAlertContainer));
    }

    public void clickOkBtn() {
        driver.findElement(okBtn).click();
    }
}
