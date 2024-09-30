package rus.yanov.coffeemachine.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rus.yanov.coffeemachine.dto.request.IngredientRequestDto;
import rus.yanov.coffeemachine.dto.response.IngredientResponseDto;
import rus.yanov.coffeemachine.entity.Ingredient;
import rus.yanov.coffeemachine.repository.IngredientRepository;
import rus.yanov.coffeemachine.utils.mapper.IngredientMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientService {

    private final IngredientRepository          ingredientRepository;
    private final IngredientMapper              ingredientMapper;

    /**
     * Метод для получения списка всех доступных ингридиентов.
     */
    public List<IngredientResponseDto> getAllIngredients() {

        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::entityToResponseDto)
                .collect(Collectors.toList());

    }

    /**
     * Метод для получения ингридиента по ID.
     */
    public IngredientResponseDto getIngredientById(UUID id) {

        return ingredientMapper.entityToResponseDto(ingredientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity with id " + id + " not found")
        ));
    }

    /**
     * Метод для добавления нового ингридиента.
     */
    @Transactional
    public IngredientResponseDto addIngredient(IngredientRequestDto ingredientRequestDto) {

        Ingredient ingredient = ingredientRepository.save(ingredientMapper.requestDtoToEntity(ingredientRequestDto));
        return ingredientMapper.entityToResponseDto(ingredient);

    }

    /**
     * Метод для пополнения ингридиента.
     */
    @Transactional
    public IngredientResponseDto refillIngredient(UUID id) {

        Ingredient ingredient = null;
        try {
            ingredient = ingredientRepository.findById(id).get();
            ingredient.expense(0);
            ingredient.quantityStatus(Ingredient.QuantityStatus.FULL);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
        return ingredientMapper.entityToResponseDto(ingredient);

    }

}
