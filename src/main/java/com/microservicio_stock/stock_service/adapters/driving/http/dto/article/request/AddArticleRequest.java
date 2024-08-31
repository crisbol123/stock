package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

import static com.microservicio_stock.stock_service.configuration.Constants.*;

@Getter
@Setter
public class AddArticleRequest {

    @NotBlank(message = NAME_NOT_BLANK)
    @Size(max = 50, message = NAME_SIZE)
    private String name;

    @NotBlank(message = DESCRIPTION_NOT_BLANK)
    @Size(max = 90, message = DESCRIPTION_SIZE)
    private String description;

    @Min(value = 1, message = QUANTITY_MIN)
    private int quantity;

    @Positive(message = PRICE_POSITIVE)
    private double price;

    @NotEmpty(message = CATEGORIES_NOT_EMPTY)
    @Size(min = 1, max = 3, message = CATEGORIES_SIZE)
    @UniqueElements(message = CATEGORIES_UNIQUE)
    private List<@NotNull Long> categoryIds;

    @NotNull(message = MARK_ID_NOT_NULL)
    private Long markId;
}
