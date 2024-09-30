package rus.yanov.coffeemachine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import rus.yanov.coffeemachine.dto.request.IngredientRequestDto;
import rus.yanov.coffeemachine.dto.response.IngredientResponseDto;
import rus.yanov.coffeemachine.service.IngredientService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Ингредиенты", description = "API для взаимодействия с ингредиентами кофе")
@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @Operation(
            summary = "Получение всех ингредиентов",
            description = "Запрос выдает все имеющиеся ингредиенты"
    )
    @GetMapping("/all")
    public List<IngredientResponseDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @Operation(
            summary = "Получение ингредиента по id"
    )
    @GetMapping("/{id}")
    public IngredientResponseDto getIngredientById(@PathVariable UUID id) {
        return ingredientService.getIngredientById(id);
    }

    @Operation(
            summary = "Добавление нового ингредиента",
            description = "Для этого запроса id заказа передавать не нужно"
    )
    @PostMapping("/add")
    public IngredientResponseDto addIngredient(@RequestBody IngredientRequestDto ingredientRequestDto) {
        return ingredientService.addIngredient(ingredientRequestDto);
    }

    @Operation(
            summary = "Пополнение ингредиента в кофемащину по id"
    )
    @PutMapping("/{id}/refill")
    public IngredientResponseDto refillIngredient(@PathVariable UUID id) {
        return refillIngredient(id);
    }
}
