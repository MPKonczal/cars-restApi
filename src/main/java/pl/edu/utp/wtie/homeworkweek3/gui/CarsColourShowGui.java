package pl.edu.utp.wtie.homeworkweek3.gui;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.model.CarColour;
import pl.edu.utp.wtie.homeworkweek3.service.CarService;

@Route("show-colour")
public class CarsColourShowGui extends VerticalLayout implements quickReturn {

    private CarService carService;

    @Autowired
    public CarsColourShowGui(CarService carService) {
        this.carService = carService;

        Grid<Car> gridCars = new Grid<>(Car.class);
        gridCars.setColumns("id", "mark", "model", "colour");

        ComboBox<CarColour> comboBoxColours = new ComboBox<>("Colours");
        comboBoxColours.setItems(CarColour.values());
        comboBoxColours.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()) {
                add(new Label("No colour selected"));
            } else {
                gridCars.setItems(carService.getCarListColored(comboBoxColours.getValue()));
                add(gridCars);
            }
        });

        add(comboBoxColours, addButtonBack());
    }
}
