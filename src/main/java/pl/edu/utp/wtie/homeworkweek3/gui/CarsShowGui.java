package pl.edu.utp.wtie.homeworkweek3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.service.CarService;

@Route("show-cars")
public class CarsShowGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarsShowGui(CarService carService) {
        this.carService = carService;

        Grid<Car> gridCars = new Grid<>(Car.class);
        gridCars.setColumns("id", "mark", "model", "colour");
        gridCars.setItems(carService.getCarList());

        Button buttonBack = new Button("Back", new Icon(VaadinIcon.ARROW_LEFT));
        buttonBack.addClickListener(buttonClickEvent -> {
            buttonBack.getUI().ifPresent(ui -> ui.navigate("main"));
        });

        add(gridCars, buttonBack);
    }
}
