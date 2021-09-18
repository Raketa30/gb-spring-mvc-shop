package ru.geekbrains.shop.buisness.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.shop.buisness.domain.UserEntity;
import ru.geekbrains.shop.buisness.domain.dto.UserDto;
import ru.geekbrains.shop.buisness.domain.search.UserSearchCondition;

public interface UserService {
    boolean addUser(UserEntity user);

    Page<UserDto> findAllPaginated(UserSearchCondition userSearchCondition);

    void updateUser(UserDto user);

    UserDto findUserDto(Long id);

    void deleteUser(Long id);
}
