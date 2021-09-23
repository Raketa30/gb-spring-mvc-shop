package ru.geekbrains.shop.buisness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.shop.buisness.domain.RoleEntity;
import ru.geekbrains.shop.buisness.repository.RoleRepository;
import ru.geekbrains.shop.buisness.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }
}
