package ru.geekbrains.shop.buisness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shop.buisness.domain.RoleEntity;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByName(String roleName);
}
