package rus.yanov.coffeemachine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rus.yanov.coffeemachine.dto.request.OrderRequestDto;
import rus.yanov.coffeemachine.dto.response.OrderResponseDto;
import rus.yanov.coffeemachine.service.OrderService;

@Tag(name = "Заказы", description = "API для взаимодействия с заказами кофе")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Создание нового заказа",
            description = "Для этого запроса id заказа передавать не нужно"
    )
    @PostMapping("/create")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderCreateRequestDto) {
        return orderService.createOrder(orderCreateRequestDto);
    }
}
