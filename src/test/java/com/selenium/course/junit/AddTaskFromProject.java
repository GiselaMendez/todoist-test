package com.selenium.course.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.selenium.course.pages.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Carlos Gonzales on 6/1/2015.
 */
@RunWith(Parameterized.class)
public class AddTaskFromProject {
    private MainApp mainApp;
    private ProjectEditor projectEditor;
    private LeftMenu leftMenu;
    String projectName;

    String taskName;
    int  priority;

    public AddTaskFromProject(String taskName, int priority) {
        this.taskName = taskName;
        this.priority = priority;
    }

    @Before
    public void setUp() {
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

    @Test
    public void testUntitled() {
        AddTask addTask = projectEditor.clickAddTaskLnk();
        projectEditor = addTask.setTaskNameTxt(taskName)
                .selectPriority(priority)
                .setDate("")
                .selectProject("")
                .clickAddTaskBtn();

        Assert.assertTrue("Task should be added",
                projectEditor.isTaskAdded(taskName));
    }

    @After
    public void tearDown() {
        leftMenu.clickProjectMenu(projectName);
        DeleteAlert deleteAlert = leftMenu.clickDeleteProject(projectName);
        deleteAlert.clickOkBtn();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getValues() {
        return Arrays.asList(new Object[][]{{"first Task", 1},
                {"second Task", 2}, {"third Task", 3}, {"fourth Task", 4}});
    }
}
