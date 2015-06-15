package com.selenium.course.pages;
import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
public class LeftMenu{
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "left_menu")
    private WebElement divToolbarMenu;

    private By tdLogoImg = By.cssSelector("img.cmp_td_logo_min");

    @FindBy(linkText = "Add Project")
    private WebElement addProjectLnk;

    @FindBy(name = "ta")
    private WebElement nameProjectTxt;

    @FindBy(id = "color_selector")
    private WebElement colorMenuBtn;

    @FindBy(css = "li.colors")
    private WebElement colorMenu;

    @FindBy(xpath = "//span[contains(.,'Add Project')]")
    private WebElement addProjectBtn;

    private By menuOption = By.cssSelector("td.menu");

    @FindBy(xpath = "//td[contains(.,'Edit project')]")
    private WebElement editProjectOption;

    @FindBy(linkText = "Save")
    private WebElement saveProjectBtn;

    @FindBy(xpath = "//td[contains(.,'Delete project')]")
    private WebElement deleteProjectOption;

    public LeftMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public AddProject clickAddProjectLnk() {
        addProjectLnk.click();
        return new AddProject(driver);
    }

    public boolean isProjectDisplayed(String projectName) {
        return isElementPresent(driver.findElement(By.xpath("//td[contains(.,'" + projectName + "')]")));
    }

    public void clickProjectMenu(String projectName) {
        WebElement projectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(.,'" + projectName + "')]")));
        Action action = new Actions(driver).contextClick(projectElement).build();
        action.perform();
    }

    public void clickEditProject(String projectName) {
        clickProjectMenu(projectName);
        editProjectOption.click();
    }

    public void clickSaveProjectBtn() {
        saveProjectBtn.click();
    }

    public DeleteAlert clickDeleteProject(String projectName) {
        clickProjectMenu(projectName);
        deleteProjectOption.click();
        return new DeleteAlert(driver);
    }

    public ProjectEditor clickProjectName(String projectName) {
        driver.findElement(By.xpath("//td[contains(.,'" + projectName + "')]")).click();
        return new ProjectEditor(driver);
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
