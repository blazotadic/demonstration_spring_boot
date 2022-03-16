package com.example.demonstration_spring_boot.contoller;

import com.example.demonstration_spring_boot.dto.RoleDTO;
import com.example.demonstration_spring_boot.entity.Role;
import com.example.demonstration_spring_boot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/role/")
public class RoleController {

    private final RoleService roleService;

    @PostMapping(value = "store")
    public ResponseEntity<Role> store(@RequestBody RoleDTO roleDTO)
    {
        if (roleDTO.getId() != null) {  //ako se proslijedi id bice update
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = roleService.save(roleDTO);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Role> update(@PathVariable Integer id, @RequestBody RoleDTO roleDTO)
    {
        if (roleDTO.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Role> roleOptional = roleService.update(id, roleDTO);
        return roleOptional
            .map(role -> new ResponseEntity<>(role, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Role>> all()
    {
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
