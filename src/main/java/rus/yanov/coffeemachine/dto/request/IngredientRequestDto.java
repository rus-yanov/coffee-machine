package rus.yanov.coffeemachine.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rus.yanov.coffeemachine.entity.Ingredient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Schema(description = "DTO для обновления информации об ингридиентах")
public class IngredientRequestDto {

    @Schema(description = "Нзвание ингидиента", example = "молоко")
    @JsonProperty("name")
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Ingredient.Type type;

}
