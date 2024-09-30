package rus.yanov.coffeemachine.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rus.yanov.coffeemachine.dto.response.OrderResponseDto;
import rus.yanov.coffeemachine.entity.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CoffeeMapper          coffeeMapper;

    public OrderResponseDto entityToResponseDto(Order order){

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.id(order.id());
        orderResponseDto.comment(order.comment());
        orderResponseDto.timestamp(order.timestamp());
        orderResponseDto.coffeeResponseDtoList(
                order.coffeeList().stream()
                        .map(coffeeMapper::entityToResponseDto)
                        .collect(Collectors.toList())
        );
        return orderResponseDto;
    }

}
