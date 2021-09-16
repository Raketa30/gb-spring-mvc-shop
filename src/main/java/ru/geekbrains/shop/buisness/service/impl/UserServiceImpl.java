package ru.geekbrains.shop.buisness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.shop.buisness.domain.RoleEntity;
import ru.geekbrains.shop.buisness.domain.UserEntity;
import ru.geekbrains.shop.buisness.domain.dto.UserDto;
import ru.geekbrains.shop.buisness.domain.search.UserSearchCondition;
import ru.geekbrains.shop.buisness.repository.RoleRepository;
import ru.geekbrains.shop.buisness.repository.UserRepository;
import ru.geekbrains.shop.buisness.service.UserService;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addUser(UserEntity user) {
        Optional<UserEntity> optional = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (optional.isPresent()) {
            return false;
        }

        RoleEntity userRole = roleRepository.findRoleEntityByName("ROLE_USER");
        user.setActive(true);
        user.setRoles(Collections.singleton(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return false;
    }

    @Override
    public Page<UserDto> findAllPaginated(UserSearchCondition condition) {
        Pageable pageRequest = PageRequest.of(
                condition.getPageNum() - 1,
                condition.getPageSize(),
                Sort.by(condition.getSortDirection(), condition.getSortField())
        );

        Page<UserEntity> productEntityPage = userRepository.findAll(pageRequest);
        return productEntityPage.map(this::getUserDtoFromEntity);
    }

    private UserDto getUserDtoFromEntity(UserEntity entity) {
        return UserDto.builder().id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .active(entity.isActive())
                .roles(entity.getRoles())
                .build();
    }
}
