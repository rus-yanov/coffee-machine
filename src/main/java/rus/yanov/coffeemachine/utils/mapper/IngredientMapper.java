package rus.yanov.coffeemachine.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rus.yanov.coffeemachine.dto.request.IngredientRequestDto;
import rus.yanov.coffeemachine.dto.response.IngredientResponseDto;
import rus.yanov.coffeemachine.entity.Ingredient;

@Component
@RequiredArgsConstructor
public class IngredientMapper {

    public Ingredient requestDtoToEntity(IngredientRequestDto ingredientRequestDto){

        Ingredient ingredient = new Ingredient();
        ingredient.name(ingredientRequestDto.name());
        ingredient.type(ingredientRequestDto.type());
        ingredient.expense(0);
        ingredient.quantityStatus(Ingredient.QuantityStatus.FULL);
        return ingredient;
    }

    public IngredientResponseDto entityToResponseDto(Ingredient ingredient){

        IngredientResponseDto ingredientResponseDto = new IngredientResponseDto();
        ingredientResponseDto.id(ingredient.id());
        ingredientResponseDto.name(ingredient.name());
        ingredientResponseDto.type(ingredient.type());
        ingredientResponseDto.quantityStatus(ingredient.quantityStatus());
        return ingredientResponseDto;
    }

}
