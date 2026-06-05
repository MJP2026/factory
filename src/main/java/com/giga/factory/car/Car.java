package com.giga.factory.car;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Car entity")
public class Car {

    @Schema(description = "Unique identifier of the car", example = "1")
    private Long id;

    @Schema(description = "Color of the car", example = "Red")
    private String color;

    @Schema(description = "Year the car was constructed", example = "2022")
    private Integer yearOfConstruction;

    @Schema(description = "Brand of the car", example = "Toyota")
    private String brand;

    @Schema(description = "Engine power in horsepower", example = "150")
    private Integer power;

    public Car() {}

    public Car(Long id, String color, Integer yearOfConstruction, String brand, Integer power) {
        this.id = id;
        this.color = color;
        this.yearOfConstruction = yearOfConstruction;
        this.brand = brand;
        this.power = power;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Integer getYearOfConstruction() { return yearOfConstruction; }
    public void setYearOfConstruction(Integer yearOfConstruction) { this.yearOfConstruction = yearOfConstruction; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Integer getPower() { return power; }
    public void setPower(Integer power) { this.power = power; }
}
