package com.example.demonstration_spring_boot.search;


import com.example.demonstration_spring_boot.entity.Role;
import com.example.demonstration_spring_boot.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSearchSpecification implements Specification<User> {

    private final UserSearch userSearch;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
    {
        List<Predicate> predicates = new ArrayList<>();
        Join<User, Role> roleJoin = fetchRelation(root);

        filterByIdGreater(root, criteriaBuilder, predicates);
        filterByIdLess(root, criteriaBuilder, predicates);
        filterByUsername(root, criteriaBuilder, predicates);
        filterByRoleDescription(criteriaBuilder, predicates, roleJoin);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Join<User, Role> fetchRelation(Root<User> root) {
        Fetch<User, Role> roleFetch = root.fetch("roles", JoinType.LEFT);
        return (Join<User, Role>) roleFetch;
    }

    private void filterByRoleDescription(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Join<User, Role> roleJoin) {
        if (userSearch.getRoleDescriptionContain() != null)
        {
            Predicate UsernameContain = criteriaBuilder.like(roleJoin.get("description"),
                    "%" + userSearch.getRoleDescriptionContain() + "%");
            predicates.add(UsernameContain);
        }
    }

    private void filterByUsername(Root<User> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (userSearch.getUsernameContain() != null)
        {
            Predicate UsernameContain = criteriaBuilder.like(root.get("username"),
                    "%" + userSearch.getUsernameContain() + "%");
            predicates.add(UsernameContain);
        }
    }

    private void filterByIdLess(Root<User> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (userSearch.getUserIdLess() != null)
        {
            Predicate userId = criteriaBuilder.lessThan(root.get("id"), userSearch.getUserIdLess());
            predicates.add(userId);
        }
    }

    private void filterByIdGreater(Root<User> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (userSearch.getUserIdGreater() != null)
        {
            Predicate userId = criteriaBuilder.greaterThan(root.get("id"), userSearch.getUserIdGreater());
            predicates.add(userId);
        }
    }
}
