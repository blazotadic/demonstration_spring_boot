package com.example.demonstration_spring_boot.service;

import com.example.demonstration_spring_boot.dto.CountryDTO;
import com.example.demonstration_spring_boot.entity.Country;
import com.example.demonstration_spring_boot.mapper.CountryMapper;
import com.example.demonstration_spring_boot.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;


    public Page<Country> findPageable(Pageable pageable) {
        return countryRepository.findAllUsingJPQL(pageable);

    }

    public List<Country> findAllBetterMethod(Pageable pageable)
    {
        Page<Integer> countryIdsPage = countryRepository.findIdsPageable(pageable); // [1, 2, 3]
        List<Integer> countryIds = countryIdsPage.getContent();

        return countryRepository.findByIdIn(countryIds);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void save(CountryDTO countryDTO)
    {
        countryDTO.setId(null); //jos jedan nivo provjere da se ne bi odradio update

        countryRepository.save(
            countryMapper.toEntity(countryDTO)
        );

        throw new IllegalArgumentException("Country error!");
    }

    @Transactional
    public void update(CountryDTO countryDTO)
    {
        if (countryRepository.findById(countryDTO.getId()).isPresent())
        {
            Country country = countryMapper.toEntity(countryDTO);
            countryRepository.save(country);
        }
        else {
            throw new IllegalArgumentException("Country not exists!");
        }
    }

    public void delete(Integer id)
    {
        countryRepository.deleteById(id);
    }

}
