package com.carData.service;

import com.carData.model.MyCar;
import com.carData.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepo;

    // post
    public ResponseEntity<Object> saveCarData(MyCar carData){
        if(carRepo.findById(carData.getId()).isPresent()){
            return new ResponseEntity<>("Data with given id is present",HttpStatus.BAD_REQUEST);
        }else{
            carRepo.save(carData);
            return new ResponseEntity<>(carData, HttpStatus.CREATED);
        }
    }

    // post all data

    public ResponseEntity<Object> saveAllCarData(List<MyCar> carData){

        carRepo.saveAll(carData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // get all data
    public ResponseEntity<Object> getCarData(){
        if(carRepo.findAll().isEmpty()){
            return new ResponseEntity<>("Not_Found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(carRepo.findAll(), HttpStatus.OK);
        }
    }

    // get specific data
    public ResponseEntity<Object> getCarDataById(int id){
        if (carRepo.findById(id).isPresent()){
            return new ResponseEntity<>(carRepo.findById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

    // put
    public ResponseEntity<Object> updateCarData(int id, MyCar carData){

        Optional<MyCar> myCar = carRepo.findById(id);
        if (myCar.isPresent()){
            MyCar existingCarData = myCar.get();
            existingCarData.setPrice(carData.getPrice());
            carRepo.save(existingCarData);
            return new ResponseEntity<>(existingCarData, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

    // delete
    public ResponseEntity<Object> deleteCarData(int id){
        if (carRepo.findById(id).isPresent()){
            carRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

}
