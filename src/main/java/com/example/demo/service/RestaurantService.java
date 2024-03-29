package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.entity.Restaurant;
import com.example.demo.mapper.RestaurantMapper;
import com.example.demo.repo.RestaurantRepo;


@Service
public class RestaurantService {

	@Autowired
	RestaurantRepo restaurantrepo;
       
	public List<RestaurantDTO> findAllRestaurants() {
		List<Restaurant> restaurants = restaurantrepo.findAll();
		//map it to list of DTO's
		List<RestaurantDTO> restaurantDTOList =  restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
		return restaurantDTOList;
	}

public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
		
		Restaurant savedRestaurant = restaurantrepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
			return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
	}

public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
	
			Optional<Restaurant> restaurant = restaurantrepo.findById(id);
			if(restaurant.isPresent()) {
				return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
}

}
