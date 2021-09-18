package ru.geekbrains.shop.buisness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.shop.buisness.domain.RoleEntity;
import ru.geekbrains.shop.buisness.domain.UserEntity;
import ru.geekbrains.shop.buisness.domain.dto.UserDto;
import ru.geekbrains.shop.buisness.domain.search.UserSearchCondition;
import ru.geekbrains.shop.buisness.repository.RoleRepository;
import ru.geekbrains.shop.buisness.repository.UserRepository;
import ru.geekbrains.shop.buisness.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

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
    @Transactional
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

    @Override
    @Transactional
    public void updateUser(UserDto user) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(user.getId());
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = optionalUserEntity.get();
            entity.setActive(user.isActive());
            entity.setRoles(new HashSet<>(roleRepository.findAllById(user.getRolesId())));
            userRepository.save(entity);
        }
    }

    @Override
    public UserDto findUserDto(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isPresent()) {
            return getUserDtoFromEntity(optionalUserEntity.get());
        }
        throw new IllegalArgumentException("User not found, id:" + id);
    }

    private UserDto getUserDtoFromEntity(UserEntity entity) {
        return UserDto.builder().id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .active(entity.isActive())
                .rolesId(getRolesIdList(entity.getRoles()))
                .build();
    }

    private List<Long> getRolesIdList(Set<RoleEntity> roles) {
        return roles.stream().map(RoleEntity::getId).collect(Collectors.toList());
    }
}
