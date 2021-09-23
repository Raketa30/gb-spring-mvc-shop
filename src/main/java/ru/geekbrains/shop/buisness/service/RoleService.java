package ru.geekbrains.shop.buisness.service;

import ru.geekbrains.shop.buisness.domain.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> findAll();
}
