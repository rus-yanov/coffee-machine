package rus.yanov.coffeemachine.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rus.yanov.coffeemachine.dto.request.OrderRequestDto;
import rus.yanov.coffeemachine.dto.response.OrderResponseDto;
import rus.yanov.coffeemachine.entity.Coffee;
import rus.yanov.coffeemachine.entity.Ingredient;
import rus.yanov.coffeemachine.entity.Order;
import org.slf4j.Logger;
import rus.yanov.coffeemachine.repository.CoffeeRepository;
import rus.yanov.coffeemachine.repository.IngredientRepository;
import rus.yanov.coffeemachine.repository.OrderRepository;
import rus.yanov.coffeemachine.utils.mapper.OrderMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository           orderRepository;
    private final CoffeeRepository          coffeeRepository;
    private final IngredientRepository      ingredientRepository;
    private final OrderMapper               orderMapper;

    private static final Logger 			logger = LoggerFactory.getLogger(OrderService.class);
    private static final int                SINGLE_DRINK_INGREDIENT_EXPENSE = 5;
    private static final int                MAX_EXPENSE = 100;
    private static final long               YEARS_TO_DROP_OLD_STATISTIC = 5;

    /**
     * Метод для создания заказа.
     */
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        Order order = new Order();
        List<Coffee> coffeeList = new ArrayList<>();

        for(UUID coffeeId : orderRequestDto.coffeeIds()) {
            try {
                Coffee coffee = coffeeRepository.findById(coffeeId).get();
                makeCoffee(coffee);
                coffeeList.add(coffee);
                coffee.score(coffee.score() + 1);
            } catch (Exception e) {
                logger.warn("Напиток не может быть приготовлен");
            }
        }

        order.coffeeList(coffeeList);
        order.comment(orderRequestDto.comment());
        order.timestamp(LocalDateTime.now());

        orderRepository.save(order);

        return orderMapper.entityToResponseDto(order);
    }

    private void makeCoffee(Coffee coffee) throws Exception {

        for (Ingredient ingredient : coffee.ingredients()) {
            if (ingredient.expense() > MAX_EXPENSE - SINGLE_DRINK_INGREDIENT_EXPENSE) {
                throw new Exception("Недостаточно " + ingredient.name());
            }
            ingredient.expense(ingredient.expense() + SINGLE_DRINK_INGREDIENT_EXPENSE);
            ingredient.quantityStatus(Ingredient.QuantityStatus.NOT_FULL);
            ingredientRepository.save(ingredient);
        }
    }

    /**
     * Метод удаления всех старых заказов.
     */
    @Transactional
    public void deleteOldOrders() {
        LocalDateTime fiveYearsAgo = LocalDateTime.now().minus(YEARS_TO_DROP_OLD_STATISTIC, ChronoUnit.YEARS);
        List<Order> oldOrders = orderRepository.findOrdersOlderThan(fiveYearsAgo);
        orderRepository.deleteAll(oldOrders);
    }

}
