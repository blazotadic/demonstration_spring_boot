package com.example.demonstration_spring_boot;

import com.example.demonstration_spring_boot.entity.City;
import com.example.demonstration_spring_boot.entity.Country;
import com.example.demonstration_spring_boot.repository.CityRepository;
import com.example.demonstration_spring_boot.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = DemonstrationSpringBootApplication.class)
public class CityIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.example.demonstration_spring_boot.CityIntegrationTest.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Test
    public void getAllCitiesFromGivenCountryTest()
    {
        List<City> cities = cityRepository.findAllCitiesFromGivenCountry("ME");
        LOGGER.info("Cities: {}", cities);
    }

    @Test
    @Transactional
    public void getOneCityByIdTest()
    {
        Optional<City> cityOptional = cityRepository.findById(2);
        if (cityOptional.isPresent())
        {
            City city = cityOptional.get();
            Country country = city.getCountry();
        }
    }

    @Test
    @Transactional
    public void getAllCities()
    {
        List<City> cities = cityRepository.findAllWithCountries();
        for (City city : cities)
        {
            Country country = city.getCountry();
        }
    }


    @Test
    @Transactional
    public void insertParisTest()
    {
        Country country = countryRepository.getById(1); // NO-SELECT

        City city = new City();
        city.setName("Marseille");
        city.setCountry(country);


        cityRepository.save(city);
    }
}
