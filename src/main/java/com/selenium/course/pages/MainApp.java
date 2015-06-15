package com.selenium.course.pages;

import com.selenium.course.framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Carlos Gonzales on 5/31/2015.
 */
public class MainApp {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainApp(WebDriver driver) {
        this.driver = driver;
        wait = DriverManager.getInstance().getWait();
    }

    public TopBar goToTopBar() {
        return new TopBar(driver);
    }

    public LeftMenu goToLeftMenu() {
        return new LeftMenu(driver);
    }
}
