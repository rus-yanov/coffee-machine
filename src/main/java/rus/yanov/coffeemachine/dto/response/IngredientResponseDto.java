package rus.yanov.coffeemachine.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import rus.yanov.coffeemachine.entity.Ingredient;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Schema(description = "DTO для передачи полной информации об ингридиентах")
public class IngredientResponseDto {

    @Schema(description = "id ингридиента")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "Нзвание ингидиента", example = "молоко")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Сколько ингридиента было потрачено в % с момента последнего пополнения")
    @JsonProperty("expenses")
    private Integer expenses;

    @Schema(description = "Тип ингридиента")
    @JsonProperty("type")
    private Ingredient.Type type;

    @Schema(description = "Количество ингридиента в кофемашине")
    @JsonProperty("quantityStatus")
    private Ingredient.QuantityStatus quantityStatus;

}
