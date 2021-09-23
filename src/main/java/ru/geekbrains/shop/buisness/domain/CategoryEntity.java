package ru.geekbrains.shop.buisness.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", schema = "shop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"subCategories"})
@EqualsAndHashCode(exclude = {"id", "subCategories"})
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Имя категории обязательно")
    private String title;

    @Column(name = "alias")
    @NotBlank(message = "Алиас категории обязательно")
    private String alias;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<CategoryEntity> subCategories;
}
