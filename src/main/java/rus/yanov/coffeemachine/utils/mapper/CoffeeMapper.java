package rus.yanov.coffeemachine.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rus.yanov.coffeemachine.dto.request.CoffeeRequestDto;
import rus.yanov.coffeemachine.dto.response.CoffeeResponseDto;
import rus.yanov.coffeemachine.entity.Coffee;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CoffeeMapper {

    private final IngredientMapper          ingredientMapper;

    public Coffee requestDtoToEntity(CoffeeRequestDto coffeeRequestDto) {

        Coffee coffee = new Coffee();
        coffee.name(coffeeRequestDto.name());
        coffee.ingredients(
                coffeeRequestDto.ingredientRequestDtoList().stream()
                        .map(ingredientMapper::requestDtoToEntity)
                        .collect(Collectors.toList())
        );
        coffee.score(0L);
        return coffee;
    }

    public CoffeeResponseDto entityToResponseDto(Coffee coffee){

        CoffeeResponseDto coffeeResponseDto = new CoffeeResponseDto();
        coffeeResponseDto.id(coffee.id());
        coffeeResponseDto.name(coffee.name());
        coffeeResponseDto.score(coffee.score());
        coffeeResponseDto.ingredientResponseDtoList(
                coffee.ingredients().stream()
                        .map(ingredientMapper::entityToResponseDto)
                        .collect(Collectors.toList())
        );
        return coffeeResponseDto;
    }

}
