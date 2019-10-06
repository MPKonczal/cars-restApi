package pl.edu.utp.wtie.homeworkweek3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.model.CarColour;
import pl.edu.utp.wtie.homeworkweek3.model.CarMark;
import pl.edu.utp.wtie.homeworkweek3.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars",
                produces = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
public class CarApi {
    private CarService carService;

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carService.getCarList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> firstCar = carService.getFirstCar(id);
        if (firstCar.isPresent()) {
            return new ResponseEntity<>(firstCar.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/colours/{colour}")
    public ResponseEntity<List<Car>> getCarsByColour(@PathVariable CarColour colour) {
        List<Car> carListColored = carService.getCarListColored(colour);
        if (!carListColored.isEmpty()) {
            return new ResponseEntity<>(carListColored, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        if (carService.addNewCar(car)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modCar(@RequestBody Car car) {
        if (carService.changeCar(car)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity modCarById(@PathVariable long id,
                                     @RequestParam(required = false) CarMark mark,
                                     @RequestParam(required = false) String model,
                                     @RequestParam(required = false) CarColour colour) {
        Optional<Car> firstCar = carService.getFirstCar(id);
        if (firstCar.isPresent()) {
            Car newCar = firstCar.get();
            if (mark != null) {
                newCar.setMark(mark);
            }
            if (model != null) {
                newCar.setModel(model);
            }
            if (colour != null) {
                newCar.setColour(colour);
            }
            carService.getCarList().remove(firstCar.get());
            carService.getCarList().add(newCar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        if (carService.deleteCar(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
