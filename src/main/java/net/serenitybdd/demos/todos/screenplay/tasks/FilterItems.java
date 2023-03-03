package net.serenitybdd.demos.todos.screenplay.tasks;

import net.serenitybdd.demos.todos.screenplay.model.TodoStatusFilter;
import net.serenitybdd.demos.todos.screenplay.user_interface.TodoList;
import net.serenitybdd.demos.todos.utils.Screenshotutils1;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.webdriver.WebDriverFacade;

public class FilterItems {
    public static Performable toShow(TodoStatusFilter filter) {
        return Task.where("{0} filters items by " + filter,
                Click.on(TodoList.FILTER.of(filter.name()))
        );
    }
}