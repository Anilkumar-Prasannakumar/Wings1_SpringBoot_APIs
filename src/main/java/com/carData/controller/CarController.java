package com.carData.controller;

import com.carData.model.MyCar;
import com.carData.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carData")
public class CarController {

    @Autowired
    private CarService carServ;

    // post
    @PostMapping("/addCarData")
    public ResponseEntity<Object> saveCarData(@RequestBody MyCar carData){

        return carServ.saveCarData(carData);
    }

    // post a list of data
    @PostMapping("/addAllCarData")
    public ResponseEntity<Object> saveAllCarData(@RequestBody List<MyCar> carData){
        return carServ.saveAllCarData(carData);
    }

    // get all data
    @GetMapping("/getAllCarData")
    public ResponseEntity<Object> getCarData(){

        return carServ.getCarData();
    }

    // get specific data
    @GetMapping("/getCarData/{id}")
    public ResponseEntity<Object> getCarDataById(@PathVariable int id){

        return carServ.getCarDataById(id);
    }

    // put
    @PutMapping("/updateCarData/{id}")
    public ResponseEntity<Object> updateCarData(@PathVariable int id,@RequestBody MyCar carData){
        return carServ.updateCarData(id,carData);
    }

    // delete
    @DeleteMapping("/deleteCarData/{id}")
    public ResponseEntity<Object> deleteCarData(@PathVariable int id){
        return carServ.deleteCarData(id);
    }
}
