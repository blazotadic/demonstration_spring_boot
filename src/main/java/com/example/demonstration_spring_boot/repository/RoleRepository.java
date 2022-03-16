package com.example.demonstration_spring_boot.repository;

import com.example.demonstration_spring_boot.dto.RoleDTO;
import com.example.demonstration_spring_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{

    List<RoleDTO> findByNameStartingWithOrDescriptionStartingWith(
            String namePart, String descriptionPart
    );

    @Query(value = "select role from Role role where role.id = :id")
    Role findOneById(Integer id);
}
