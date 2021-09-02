package ru.geekbrains.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shop.domain.CategoryEntity;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTree {
    private List<TreeEntry> rootCategories;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeEntry {
        private CategoryEntity category;
        private List<TreeEntry> subCategories;
    }
}
