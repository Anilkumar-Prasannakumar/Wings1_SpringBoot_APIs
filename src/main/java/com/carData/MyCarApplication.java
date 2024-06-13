package com.carData;

import com.carData.model.MyCar;
import com.carData.model.Role;
import com.carData.model.UserInfo;
import com.carData.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@SpringBootApplication
public class MyCarApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyCarApplication.class, args);
//		UserInfo user = new UserInfo();
//
//		System.out.println("Authority : "+ List.of(new SimpleGrantedAuthority(user.getRole().name())));
	}

}
