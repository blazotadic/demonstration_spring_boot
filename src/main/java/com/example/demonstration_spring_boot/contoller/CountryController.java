package com.example.demonstration_spring_boot.contoller;

import com.example.demonstration_spring_boot.dto.CountryDTO;
import com.example.demonstration_spring_boot.entity.Country;
import com.example.demonstration_spring_boot.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping(value =  "/pageable")
    public ResponseEntity<Page<Country>> findPageable(Pageable pageable)
    {
        Page<Country> countryPage = countryService.findPageable(pageable);
        return new ResponseEntity<>(countryPage, HttpStatus.OK);
    }

    @GetMapping(value = "/pageable/better/method")
    public ResponseEntity<List<Country>> findAllBetterMethod(Pageable pageable)
    {
        List<Country> countries = countryService.findAllBetterMethod(pageable);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> store(@RequestBody CountryDTO countryDTO)
    {
        if (countryDTO.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        countryService.save(countryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201!
    }

    @PutMapping(value = "update")
    public ResponseEntity<Void> update( @RequestBody CountryDTO countryDTO)
    {
        countryService.update(countryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id)
    {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204!
    }
}
