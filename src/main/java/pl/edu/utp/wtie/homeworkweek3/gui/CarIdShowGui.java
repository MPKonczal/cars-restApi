package pl.edu.utp.wtie.homeworkweek3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.service.CarService;

import java.util.Optional;

@Route("show-id")
public class CarIdShowGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarIdShowGui(CarService carService) {
        this.carService = carService;

        NumberField numberFieldId = new NumberField("Enter the car id");

        Grid<Car> gridCars = new Grid<>(Car.class);
        gridCars.setColumns("id", "mark", "model", "colour");

        Button buttonShow = new Button("Show");
        buttonShow.addClickListener(buttonClickEvent -> {
            Optional<Car> firstCar = carService.getFirstCar(numberFieldId.getValue().longValue());
            if (firstCar.isPresent()) {
                numberFieldId.setLabel("Enter the car id");
                gridCars.setItems(firstCar.get());
                add(gridCars);
            } else {
                numberFieldId.setLabel("No car with this id");
            }
        });

        Button buttonBack = new Button("Back", new Icon(VaadinIcon.ARROW_LEFT));
        buttonBack.addClickListener(buttonClickEvent ->
                buttonBack.getUI().ifPresent(ui -> ui.navigate("main")));

        add(numberFieldId, buttonShow, buttonBack);
    }
}
