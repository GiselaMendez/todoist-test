package com.selenium.course.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import com.selenium.course.pages.*;

/**
 * Created by carlos_gonzales on 27-05-15.
 */
public class QuickAddTask {

    private MainApp mainApp;
    private ProjectEditor projectEditor;
    String taskName;

    @BeforeClass
    public void setUp() {
        HomePage homePage = new HomePage();
        mainApp = homePage.loginAsPrimaryUser();
    }

    @Test(dataProvider = "getValues")
    public void testUntitled(String taskName, int priority) {
        this.taskName = taskName;

        TopBar topBar = mainApp.goToTopBar();
        AddTask addTask = topBar.clickAddTaskIcon();
        addTask.setTaskNameTxt(taskName);
        addTask.selectPriority(priority);
        projectEditor = addTask.clickAddTaskBtn();

        Assert.assertTrue(projectEditor.isTaskAdded(taskName),
                "Task should be added");
    }

    @AfterMethod
    public void tearDown() {
        projectEditor.clickTaskMenu(taskName);
        projectEditor.clickDeleteTask();
    }

    @DataProvider
    public Object[][] getValues() {
        return new Object[][]{{"first Task", 1}, {"second Task", 2},
                {"third Task", 3}, {"fourth Task", 4}};
    }
}
