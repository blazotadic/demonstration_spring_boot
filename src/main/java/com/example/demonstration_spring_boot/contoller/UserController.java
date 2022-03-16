package com.example.demonstration_spring_boot.contoller;

import com.example.demonstration_spring_boot.dto.RoleDTO;
import com.example.demonstration_spring_boot.dto.UserWithDetailDTO;
import com.example.demonstration_spring_boot.entity.User;
import com.example.demonstration_spring_boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;


    @PutMapping(value = "{id}/add-role")
    public ResponseEntity<User> updateUserRole(@PathVariable Integer id, @RequestBody RoleDTO role)
    {
        User user = userService.addRole(id, role);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<UserWithDetailDTO>> all()
    {
        List<UserWithDetailDTO> users = userService.all();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping(value = "exists/by-username/{username}")
    public ResponseEntity<Map<String, Boolean>> existsByUsername(@PathVariable String username)
    {
        boolean existsStatus = userService.existsByUsername(username);
        return new ResponseEntity<>(Collections.singletonMap("status", existsStatus), HttpStatus.OK);
    }

    @PostMapping(value = "store")
    public ResponseEntity<UserWithDetailDTO> store(@RequestBody UserWithDetailDTO userWithDetailDTO)
    {
        UserWithDetailDTO user = userService.save(userWithDetailDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
