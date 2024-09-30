package rus.yanov.coffeemachine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import rus.yanov.coffeemachine.dto.request.CoffeeRequestDto;
import rus.yanov.coffeemachine.dto.response.CoffeeResponseDto;
import rus.yanov.coffeemachine.service.CoffeeService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Напитки", description = "API для взаимодействия с напитками в ассортименте")
@RestController
@RequestMapping("/api/coffee")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Operation(
            summary = "Получение всех напитков",
            description = "Запрос выдает все имеющиеся напитки"
    )
    @GetMapping("/all")
    public List<CoffeeResponseDto> getAllDrinks() {
        return coffeeService.getAllDrinks();
    }

    @Operation(
            summary = "Получение напитка по id"
    )
    @GetMapping("/{id}")
    public CoffeeResponseDto getDrinkById(@PathVariable UUID id) {
        return coffeeService.getDrinkById(id);
    }

    @Operation(
            summary = "Получение самого популярного напитка из ассортимена кофемашины"
    )
    @GetMapping("/get-most-popular")
    public CoffeeResponseDto getMostPopularCoffee() {
        return coffeeService.getMostPopularCoffee();
    }

    @Operation(
            summary = "Добавление нового напитка в ассортимент",
            description = "Для этого запроса id заказа передавать не нужно"
    )
    @PostMapping("/add")
    public CoffeeResponseDto addDrink(@RequestBody CoffeeRequestDto coffeeRequestDto) {
        return coffeeService.addDrink(coffeeRequestDto);
    }

}
