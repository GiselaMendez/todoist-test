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
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Carlos Gonzales on 5/31/2015.
 */
public class AddProject {
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

    private String name, color;

    public AddProject(WebDriver driver) {
        this.driver = driver;
        this.wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public AddProject(AddProjectBuilder builder) {
        this.name = builder.getName();
        this.color = builder.getColor();
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public ProjectEditor createTask() {
        if(!name.isEmpty()) {
            setNameProject(name);
            selectProjectColor(color);
        }
        return clickAddProjectBtn();
    }

    public void setNameProject(String projectName) {
        nameProjectTxt.clear();
        nameProjectTxt.sendKeys(projectName);
    }

    public void clickColorBtn() {
        colorMenuBtn.click();
    }

    public void selectProjectColor(String optionColor) {
        clickColorBtn();
        WebElement colorMenuElement = wait.until(ExpectedConditions.visibilityOf(colorMenu));
        colorMenuElement.findElement(By.xpath("//a[contains(@style, '" + optionColor + "')]")).click();
    }

    public ProjectEditor clickAddProjectBtn() {
        addProjectBtn.click();
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
