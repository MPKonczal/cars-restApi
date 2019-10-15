package pl.edu.utp.wtie.homeworkweek3.service;

import org.springframework.stereotype.Service;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.model.CarColour;
import pl.edu.utp.wtie.homeworkweek3.model.CarMark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> carList;

    public CarService() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1L, CarMark.AUDI, "RS7", CarColour.SILVER));
        carList.add(new Car(2L, CarMark.MCLAREN, "Senna", CarColour.BLUE));
        carList.add(new Car(3L, CarMark.RENAULT, "Clio", CarColour.YELLOW));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public Optional<Car> getFirstCar(long id) {
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    public List<Car> getCarListColored(CarColour colour) {
        return carList.stream().filter(car -> car.getColour() == colour).collect(Collectors.toList());
    }

    public boolean addNewCar(Car car) {
        if (car.getMark() != null && !car.getModel().isEmpty() && car.getColour() != null) {
            return carList.add(car);
        }
        return false;
    }

    public boolean changeCar(Car newCar) {
        Optional<Car> firstCar = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if (firstCar.isPresent()) {
            Car modCar = firstCar.get();
            modCar.setId(newCar.getId());
            modCar.setMark(newCar.getMark());
            modCar.setModel(newCar.getModel());
            modCar.setColour(newCar.getColour());
            return true;
        }
        return false;
    }

    public boolean changeField(long id, CarMark mark, String model, CarColour colour) {
        Optional<Car> firstCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (firstCar.isPresent()) {
            Car modCar = firstCar.get();
            if (mark != null) {
                modCar.setMark(mark);
            }
            if (!model.isEmpty()) {
                modCar.setModel(model);
            }
            if (colour != null) {
                modCar.setColour(colour);
            }
            return true;
        }
        return false;
    }

    public boolean deleteCar(long id) {
        Optional<Car> firstCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (firstCar.isPresent()) {
            carList.remove(firstCar.get());
            return true;
        }
        return false;
    }
}
