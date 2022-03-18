package com.example.demonstration_spring_boot.mapper;


import com.example.demonstration_spring_boot.dto.CityDTO;
import com.example.demonstration_spring_boot.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper
{
    City mapToEntity(CityDTO cityDTO);
    CityDTO toDTO(City city);
}
