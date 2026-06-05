package com.giga.factory.car;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Cars", description = "CRUD operations for the Car resource")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    @Operation(summary = "List all cars", description = "Returns a list of all cars in the system")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of car list",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)))
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a car by ID", description = "Returns a single car identified by its ID")
    @ApiResponse(responseCode = "200", description = "Car found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)))
    @ApiResponse(responseCode = "404", description = "Car not found")
    public ResponseEntity<Car> getCarById(
            @Parameter(description = "ID of the car to retrieve", required = true)
            @PathVariable Long id) {
        return carRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new car", description = "Creates a new car and returns the created resource")
    @ApiResponse(responseCode = "201", description = "Car created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)))
    public Car createCar(
            @Parameter(description = "Car data to create", required = true)
            @RequestBody Car car) {
        car.setId(null); // ensure ID is assigned by the repository
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing car", description = "Updates all fields of an existing car")
    @ApiResponse(responseCode = "200", description = "Car updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)))
    @ApiResponse(responseCode = "404", description = "Car not found")
    public ResponseEntity<Car> updateCar(
            @Parameter(description = "ID of the car to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated car data", required = true)
            @RequestBody Car car) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        car.setId(id);
        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a car", description = "Deletes the car with the given ID")
    @ApiResponse(responseCode = "204", description = "Car deleted successfully")
    @ApiResponse(responseCode = "404", description = "Car not found")
    public ResponseEntity<Void> deleteCar(
            @Parameter(description = "ID of the car to delete", required = true)
            @PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
