package rus.yanov.coffeemachine.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import rus.yanov.coffeemachine.dto.request.IngredientRequestDto;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Schema(description = "DTO для передачи данных о напитке")
public class CoffeeResponseDto {

    @Schema(description = "id напитка")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "название напитка")
    @JsonProperty("name")
    private String name;

    @Schema(description = "рейтинг напитка по частоте заказа")
    @JsonProperty("score")
    private Long score;

    @Schema(description = "список ингридиентов напитка")
    @JsonProperty("ingredients")
    private List<IngredientResponseDto> ingredientResponseDtoList;
}
