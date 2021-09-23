package ru.geekbrains.shop.buisness.domain.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchCondition {
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortField = "id";

    private int pageNum = 1;
    private Integer pageSize = 10;
    private List<Integer> pageNumbers = Collections.singletonList(1);

    private Page<ProductDto> page;

}
