package ru.geekbrains.shop.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product", schema = "shop")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"categories"})
@EqualsAndHashCode(exclude = {"id", "categories"})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Имя продукта обязательно")
    private String title;

    @Column(name = "cost")
    @NotNull(message = "Цена продукта обязательна")
    private Double cost;

    @Column(name = "img_link")
    private String imageLink;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

}
