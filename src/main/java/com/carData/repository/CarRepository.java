package com.carData.repository;

import com.carData.model.MyCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<MyCar, Integer>{
}
