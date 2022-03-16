package com.example.demonstration_spring_boot.service;

import com.example.demonstration_spring_boot.dto.RoleDTO;
import com.example.demonstration_spring_boot.dto.UserWithDetailDTO;
import com.example.demonstration_spring_boot.entity.Role;
import com.example.demonstration_spring_boot.entity.User;
import com.example.demonstration_spring_boot.mapper.RoleMapper;
import com.example.demonstration_spring_boot.mapper.UserMapper;
import com.example.demonstration_spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;


    public UserWithDetailDTO save(UserWithDetailDTO userWithDetailDTO)
    {
        User user = userMapper.fromUserWithDetailDTO(userWithDetailDTO);
        user.syncDetails();

        userRepository.save(user);
        return userMapper.toUserWithDetailDTO(user);
    }

    public User addRole(Integer id, RoleDTO roleDTO)
    {
        Optional<User> userOptional = userRepository.findWithRolesById(id);
        if (userOptional.isPresent())
        {
            Role role = roleMapper.toEntity(roleDTO);
            User user = userOptional.get();
            user.addRole(role);

            return userRepository.save(user);
        }
        throw new EntityNotFoundException("User with id " + id + " not found!");
    }

    public List<UserWithDetailDTO> all()
    {
        List<User> users = userRepository.findAll();
        return users
            .stream()
            .map(userMapper::toUserWithDetailDTO).collect(Collectors.toList());
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
