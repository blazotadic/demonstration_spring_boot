package com.example.demonstration_spring_boot.repository;

import com.example.demonstration_spring_boot.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>
{

    @Query(
            value = "select country from Country country join fetch country.cities",
            countQuery = "select count(country) from Country country"
    )
    Page<Country> findAllUsingJPQL(Pageable pageable);

    @Query(value = "select country.id from Country country")
    Page<Integer> findIdsPageable(Pageable pageable);

    @Query(value = "select distinct country from Country country " +
            "join fetch country.cities " +
            "where country.id in (:countryIds)")
    List<Country> findByIdIn(@Param("countryIds") List<Integer> countryIds);

}
