package pl.edu.utp.wtie.homeworkweek3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainGui extends VerticalLayout {

    public MainGui() {

        Button buttonShowCars = new Button("Show all cars");
        buttonShowCars.addClickListener(buttonClickEvent ->
                buttonShowCars.getUI().ifPresent(ui -> ui.navigate("show-cars")));

        Button buttonShowCarById = new Button("Show car by id");
        buttonShowCarById.addClickListener(buttonClickEvent ->
                buttonShowCarById.getUI().ifPresent(ui -> ui.navigate("show-id")));

        Button buttonShowCarsByColours = new Button("Show cars by colour");
        buttonShowCarsByColours.addClickListener(buttonClickEvent ->
                buttonShowCarsByColours.getUI().ifPresent(ui -> ui.navigate("show-colour")));

        Button buttonAddCar = new Button("Add car");
        buttonAddCar.addClickListener(buttonClickEvent ->
                buttonAddCar.getUI().ifPresent(ui -> ui.navigate("add-car")));

        Button buttonModCar = new Button("Modify car");
        buttonModCar.addClickListener(buttonClickEvent ->
                buttonModCar.getUI().ifPresent(ui -> ui.navigate("mod-car")));

        Button buttonModCarById = new Button("Modify one field");
        buttonModCarById.addClickListener(buttonClickEvent ->
                buttonModCarById.getUI().ifPresent(ui -> ui.navigate("mod-car-field")));

        Button buttonRemoveCar = new Button("Remove car");
        buttonRemoveCar.addClickListener(buttonClickEvent ->
                buttonRemoveCar.getUI().ifPresent(ui -> ui.navigate("remove-car")));

        add(buttonShowCars, buttonShowCarById, buttonShowCarsByColours, buttonAddCar,
                buttonModCar, buttonModCarById, buttonRemoveCar);
    }
}
