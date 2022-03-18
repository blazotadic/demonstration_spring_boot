package com.example.demonstration_spring_boot.contoller;

import com.example.demonstration_spring_boot.dto.CityDTO;
import com.example.demonstration_spring_boot.exception.ValidationException;
import com.example.demonstration_spring_boot.service.CityService;
import com.example.demonstration_spring_boot.validator.CityCreateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;
    private final CityCreateValidator cityCreateValidator;


    @PostMapping(value = "custom-validator/custom-exception/create")
    public ResponseEntity<Void> create(@RequestBody CityDTO cityDTO) throws ValidationException
    {
        Errors errors = new BeanPropertyBindingResult(cityDTO, "cityDTO");
        ValidationUtils.invokeValidator(cityCreateValidator, cityDTO, errors);

        if (errors.hasErrors())
        {
            throw new ValidationException(
                "Validation error has been occurred!",
                errors
            );
        }

        cityService.save(cityDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "caching-if-id>2/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable Integer id)
    {
        CityDTO city = cityService.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping(value = "get-all")
    public ResponseEntity<List<CityDTO>> all()
    {
        List<CityDTO> cities = cityService.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id)
    {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "evict-all-cache")
    public ResponseEntity<Void> evictAllCache()
    {
        cityService.evictAllCache();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
