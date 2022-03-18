package com.example.demonstration_spring_boot.contoller;

import com.example.demonstration_spring_boot.dto.fakestore.CartExtendedDTO;
import com.example.demonstration_spring_boot.dto.fakestore.ProductFakeDTO;
import com.example.demonstration_spring_boot.dto.fakestore.ProductFakeInsertDTO;
import com.example.demonstration_spring_boot.service.FakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(value = "/api/fake/")
@RestController
public class FakeController {

    private final FakeService fakeService;

    @GetMapping(value = "all")
    public ResponseEntity<List<ProductFakeDTO>> getAllFakeProducts() {
        List<ProductFakeDTO> products = fakeService.findAllFake();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "insert")
    public ResponseEntity<ProductFakeInsertDTO> insertingFake(@RequestBody ProductFakeInsertDTO product) {
        Optional<ProductFakeInsertDTO> optionalProduct = fakeService.insertFake(product);
        return optionalProduct
                .map(productFakeInsertDTO -> new ResponseEntity<>(productFakeInsertDTO, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ProductFakeDTO> deleteFakeById(@PathVariable Integer id) {
        Optional<ProductFakeDTO> optionalProduct = fakeService.deleteFakeById(id);
        return optionalProduct
                .map(deletedProduct -> new ResponseEntity<>(deletedProduct, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "card/{id}")
    public ResponseEntity<CartExtendedDTO> oneById(@PathVariable Integer id) {
        CartExtendedDTO cart = fakeService.findFakeById(id);
        cart.setStatus("OK");
        cart.setMessage("Testna poruka");

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}