package com.example.demonstration_spring_boot.repository;

import com.example.demonstration_spring_boot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>
{

    @Query(value = "select city from City city " +
            "join fetch city.country")
    List<City> findAllWithCountries();
}
