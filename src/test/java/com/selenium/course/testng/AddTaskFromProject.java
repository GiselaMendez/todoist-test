package com.selenium.course.testng;

import com.selenium.course.framework.Environment;
import org.testng.Assert;
import org.testng.annotations.*;
import com.selenium.course.pages.*;

/**
 * Created by carlos_gonzales on 27-05-15.
 */
public class AddTaskFromProject {

    private MainApp mainApp;
    private ProjectEditor projectEditor;
    private LeftMenu leftMenu;
    String projectName;

    @BeforeClass
        projectName = "atc Project Name";
        String color = "rgb(149, 239, 99)";
        HomePage homePage = new HomePage();
        mainApp = homePage.loginAsPrimaryUser();

        leftMenu = mainApp.goToLeftMenu();
        leftMenu.clickAddProjectLnk();
        AddProject addProject = new AddProjectBuilder(projectName)
                .setColor(color).build();
        projectEditor = addProject.createTask();
    }

    @Test(dataProvider = "getValues")
    public void testUntitled(String taskName, int priority) {
        AddTask addTask = projectEditor.clickAddTaskLnk();
        projectEditor = addTask.setTaskNameTxt(taskName)
                .selectPriority(priority)
                .setDate("")
                .selectProject("")
                .clickAddTaskBtn();

        Assert.assertTrue(projectEditor.isTaskAdded(taskName), "Task should be added");
    }

    @AfterClass
    public void tearDown() {
        leftMenu.clickProjectMenu(projectName);
        DeleteAlert deleteAlert = leftMenu.clickDeleteProject(projectName);
        deleteAlert.clickOkBtn();
    }

    @DataProvider
    public Object[][] getValues() {
        return new Object[][]{{"first Task", 1}, {"second Task", 2},
                {"third Task", 3}, {"fourth Task", 4}};
    }
}
