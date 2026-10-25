package com.example.demo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(CustomerRepository repo) {
        Grid<Customer> grid = new Grid<>(Customer.class);
        TextField filter = new TextField();

        filter.setPlaceholder("Filter by last name");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> {
            if (e.getValue() == null || e.getValue().isEmpty()) {
                grid.setItems(repo.findAll());
            } else {
                grid.setItems(repo.findByLastNameStartsWithIgnoreCase(e.getValue()));
            }
        });

        grid.setColumns("id", "firstName", "lastName");
        grid.setItems(repo.findAll());

        add(filter, grid);
    }
}