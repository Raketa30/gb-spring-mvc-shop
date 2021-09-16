package ru.geekbrains.shop.buisness.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shop.buisness.domain.RoleEntity;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private boolean active;
    private Set<RoleEntity> roles;
}
