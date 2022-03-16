package com.example.demonstration_spring_boot.repository;

import com.example.demonstration_spring_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{

    List<Role> findByName(String name); // JPA - SELECT * FROM role WHERE name = name

    @Query(value = "select role from Role as role where role.name = :roleName")
    List<Role> findByNameWithJpql(@Param("roleName") String name);
    // JPQL - SELECT * FROM role WHERE name = name


}
