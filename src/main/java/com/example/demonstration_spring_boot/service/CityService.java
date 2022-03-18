package com.example.demonstration_spring_boot.service;

import com.example.demonstration_spring_boot.dto.CityDTO;
import com.example.demonstration_spring_boot.entity.City;
import com.example.demonstration_spring_boot.mapper.CityMapper;
import com.example.demonstration_spring_boot.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    //unosi se u kes ako je id veci od 2(demonstriranje nekog uslova)
    @Cacheable(value = "city", key = "#id", condition = "#id > 2")
    public CityDTO findById(Integer id)
    {
        City city = cityRepository.findById(id).orElse(null);
        return cityMapper.toDTO(city);


    }

    @Cacheable(value = "cities", key = "'all'") //poziv ove metode ce se kesirati
    public List<CityDTO> findAll()
    {
        List<City> cities = cityRepository.findAllWithCountries();
        return cities.stream().map(cityMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @CachePut(cacheNames = "city", key = "#result.id")  //dodajemo voci grad u tabelu a ujedno ga i kesiramo
    public CityDTO save(CityDTO cityDTO)
    {
        log.info("Saving new city...");

        City city = cityMapper.mapToEntity(cityDTO);
        return cityMapper.toDTO(city);
    }

    @Transactional
    @CacheEvict(cacheNames = "city", key = "#id")   //brisemo nesto iz tabele i iz kesa
    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "city", allEntries = true) //brisemo sve iz kesa sa kljucem city
    public void evictAllCache() {
        log.info("Removing all cache entries...");
    }
}
