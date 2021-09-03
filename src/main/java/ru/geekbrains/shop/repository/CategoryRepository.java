package ru.geekbrains.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shop.domain.CategoryEntity;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Set<CategoryEntity> findAllByIdIn(List<Long> ids);
}
