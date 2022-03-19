package com.example.demonstration_spring_boot.repository;

import com.example.demonstration_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query(value = "select user from User user left join fetch user.roles where user.id = :id")
    Optional<User> findWithRolesById(@Param("id") Integer id);

    boolean existsByUsername(String username);

    @Query(value = "select user " +
            "from User user " +
            "left join fetch user.userDetail " +
            "left join fetch user.roles " +
            "where user.username = :username and user.isActive = true ")
    User findByUsername(@Param("username") String username);
}
