package com.example.demonstration_spring_boot.mapper;

import com.example.demonstration_spring_boot.dto.UserWithDetailDTO;
import com.example.demonstration_spring_boot.entity.User;
import com.example.demonstration_spring_boot.security.dto.UserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper
{
    UserWithDetailDTO toUserWithDetailDTO(User user);
    User fromUserWithDetailDTO(UserWithDetailDTO userWithDetailDTO);
    User fromUserCreateToEntity(UserCreateDTO userCreateDTO);
}
