package pl.edu.utp.wtie.homeworkweek3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.model.CarColour;
import pl.edu.utp.wtie.homeworkweek3.model.CarMark;
import pl.edu.utp.wtie.homeworkweek3.service.CarService;

import java.util.Optional;

@Route("add-car")
public class CarAddGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarAddGui(CarService carService) {
        this.carService = carService;

        NumberField numberFieldId = new NumberField("Id");
        ComboBox<CarMark> comboBoxMark = new ComboBox<>("Mark");
        comboBoxMark.setItems(CarMark.values());
        TextField textFieldModel = new TextField("Model");
        ComboBox<CarColour> comboBoxColour = new ComboBox<>("Colour");
        comboBoxColour.setItems(CarColour.values());

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        Button buttonAdd = new Button("Add car");
        buttonAdd.addClickListener(buttonClick -> {
            Optional<Car> firstCar = carService.getFirstCar(numberFieldId.getValue().longValue());
            dialogCar.removeAll();
            if (firstCar.isPresent()) {
                dialogCar.add(new Label("There is already a car with this id"));
            } else {
                Car newCar = new Car(numberFieldId.getValue().longValue(), comboBoxMark.getValue(),
                        textFieldModel.getValue(), comboBoxColour.getValue());
                carService.addNewCar(newCar);
                dialogCar.add(new Label("Adding completed successfully"));
            }
            dialogCar.open();
        });

        Button buttonBack = new Button("Back", new Icon(VaadinIcon.ARROW_LEFT));
        buttonBack.addClickListener(buttonClickEvent ->
                buttonBack.getUI().ifPresent(ui -> ui.navigate("main")));

        add(numberFieldId, comboBoxMark, textFieldModel, comboBoxColour, buttonAdd, buttonBack);
    }
}
