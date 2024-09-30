package rus.yanov.coffeemachine.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rus.yanov.coffeemachine.entity.Ingredient;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Schema(description = "DTO для обновления информации о напитках")
public class CoffeeRequestDto {

    @Schema(description = "Название напитка")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Список ингридиентов напитка")
    @JsonProperty("ingredients")
    private List<IngredientRequestDto> ingredientRequestDtoList;

}
