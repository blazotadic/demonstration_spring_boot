package com.example.demonstration_spring_boot.service;

import com.example.demonstration_spring_boot.dto.fakestore.ProductFakeDTO;
import com.example.demonstration_spring_boot.dto.fakestore.ProductFakeInsertDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FakeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeService.class);

    @Qualifier("secureRestTemplate")
    private final RestTemplate secureRestTemplate;


    public List<ProductFakeDTO> findAllFake()
    {
        String url = "https://fakestoreapi.com/products";
        try
        {
            ParameterizedTypeReference<List<ProductFakeDTO>> typeReference = new ParameterizedTypeReference<>() {};

            ResponseEntity<List<ProductFakeDTO>> responseEntity = secureRestTemplate.exchange(
                    url, HttpMethod.GET, null, typeReference
            );

            return responseEntity.getBody();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Optional<ProductFakeInsertDTO> insertFake(ProductFakeInsertDTO productFakeInsert)
    {
        String url = "https://fakestoreapi.com/products";
        try
        {
            HttpEntity<ProductFakeInsertDTO> requestEntity = new HttpEntity<>(productFakeInsert);
            ResponseEntity<ProductFakeInsertDTO> responseEntity = secureRestTemplate.exchange(
                    url, HttpMethod.POST, requestEntity, ProductFakeInsertDTO.class
            );
            return Optional.ofNullable(responseEntity.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("Error has been occurred while inserting new product. Message: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<ProductFakeDTO> deleteFakeById(Integer id)
    {
        String url = "https://fakestoreapi.com/products/" + id;
        try
        {
            ResponseEntity<ProductFakeDTO> responseEntity = secureRestTemplate.exchange(
                    url, HttpMethod.DELETE, null, ProductFakeDTO.class
            );
            return Optional.ofNullable(responseEntity.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("Error has been occurred while deleting product. Message: {}", e.getMessage());
            return Optional.empty();
        }
    }

}
