package com.example.demonstration_spring_boot.service;

import com.example.demonstration_spring_boot.dto.RoleDTO;
import com.example.demonstration_spring_boot.entity.Role;
import com.example.demonstration_spring_boot.mapper.RoleMapper;
import com.example.demonstration_spring_boot.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public Role save(RoleDTO roleDTO)
    {
        Role role = roleMapper.toEntity(roleDTO);
        return roleRepository.save(role);
    }


    public Optional<Role> update(Integer id, RoleDTO roleDTO)
    {
        boolean roleExists = roleRepository.existsById(id);
        return roleExists ? Optional.of(save(roleDTO)) : Optional.empty();
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
