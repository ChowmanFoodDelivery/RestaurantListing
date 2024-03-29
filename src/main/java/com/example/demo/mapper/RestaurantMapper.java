package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.entity.Restaurant;

@Mapper
public interface RestaurantMapper {
	
	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    
	Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
    
    
	RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);

}
