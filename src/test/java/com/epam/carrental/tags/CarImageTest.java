package com.epam.carrental.tags;

import com.epam.carrental.dao.entity.Car;
import org.junit.jupiter.api.Test;

class CarImageTest {

    @Test
    void setCar() {
        Car car = new Car();
        CarImage carImage = new CarImage();
        carImage.setCar(car);
    }

    @Test
    void setClassElement() {
        CarImage carImage = new CarImage();
        carImage.setClassElement("car");
    }

    @Test
    void doStartTag() {
        CarImage carImage = new CarImage();
    }
}
