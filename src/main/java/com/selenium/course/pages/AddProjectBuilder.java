package com.selenium.course.pages;

/**
 * Created by Carlos Gonzales on 6/1/2015.
 */
public class AddProjectBuilder {

    private String name, color;

    public AddProjectBuilder(String name) {
        this.name = name;
        this.color = "rgb(204, 204, 204)";
    }

    public AddProjectBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public AddProjectBuilder setColor2(String color) {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public AddProject build() {
        return new AddProject(this);
    }
}
