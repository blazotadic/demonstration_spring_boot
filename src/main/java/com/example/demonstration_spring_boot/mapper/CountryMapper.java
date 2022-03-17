package com.example.demonstration_spring_boot.mapper;

import com.example.demonstration_spring_boot.dto.CountryDTO;
import com.example.demonstration_spring_boot.dto.RoleDTO;
import com.example.demonstration_spring_boot.entity.Country;
import com.example.demonstration_spring_boot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper
{
    CountryDTO toDTO(Country country);
    Country toEntity(CountryDTO countryDTO);
}