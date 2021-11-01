package net.lumae.panel;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        VerticalLayout todosList = new VerticalLayout();
        getThemeList().set("dark", true);
        setSizeFull();
        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        Button button = new Button("Click me!",
                event -> event.getSource().setText("Clicked!!!"));
        add(button);
        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        add(new H1("Lumae ToDo"), todosList,
                new HorizontalLayout(
                        taskField,
                        addButton,
                        button
                )
        );
    }
}
