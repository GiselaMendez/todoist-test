package com.selenium.course.junit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.selenium.course.pages.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by carlos_gonzales on 27-05-15.
 */
@RunWith(Parameterized.class)
public class QuickAddTask {

    private MainApp mainApp;
    private ProjectEditor projectEditor;

    String taskName;
    int  priority;

    public QuickAddTask(String taskName, int priority) {
        this.taskName = taskName;
        this.priority = priority;
    }

    @Before
    public void setUp() {
        HomePage homePage = new HomePage();
        mainApp = homePage.loginAsPrimaryUser();
    }

    @Test
    public void testUntitled() {
        TopBar topBar = mainApp.goToTopBar();
        AddTask addTask = topBar.clickAddTaskIcon();
        addTask.setTaskNameTxt(taskName);
        addTask.selectPriority(priority);
        projectEditor = addTask.clickAddTaskBtn();

        Assert.assertTrue("Task should be added",
                projectEditor.isTaskAdded(taskName));
    }

    @After
    public void tearDown() {
        projectEditor.clickTaskMenu(taskName);
        projectEditor.clickDeleteTask();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getValues() {
        return Arrays.asList(new Object[][]{{"first Task", 1},
                {"second Task", 2}, {"third Task", 3}, {"fourth Task", 4}});
    }
}
