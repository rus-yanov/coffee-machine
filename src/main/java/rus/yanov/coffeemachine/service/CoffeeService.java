package rus.yanov.coffeemachine.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rus.yanov.coffeemachine.dto.request.CoffeeRequestDto;
import rus.yanov.coffeemachine.dto.response.CoffeeResponseDto;
import rus.yanov.coffeemachine.entity.Coffee;
import rus.yanov.coffeemachine.repository.CoffeeRepository;
import rus.yanov.coffeemachine.utils.mapper.CoffeeMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeService {

    private final CoffeeRepository          coffeeRepository;
    private final CoffeeMapper              coffeeMapper;

    /**
     * Метод для получения списка всех напитков
     */
    public List<CoffeeResponseDto> getAllDrinks() {

        return coffeeRepository.findAll().stream()
                .map(coffeeMapper::entityToResponseDto)
                .collect(Collectors.toList());

    }

    /**
     * Метод для получения напитка по ID.
     */
    public CoffeeResponseDto getDrinkById(UUID id) {

        return coffeeMapper.entityToResponseDto(coffeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity with id " + id + " not found")
        ));
    }

    /**
     * Метод для получения самого популярного напитка.
     */
    public CoffeeResponseDto getMostPopularCoffee() {
        return coffeeMapper.entityToResponseDto(coffeeRepository.findMostPopularCoffee());
    }

    /**
     * Метод для добавления нового напитка.
     */
    @Transactional
    public CoffeeResponseDto addDrink(CoffeeRequestDto coffeeRequestDto) {

        Coffee coffee = coffeeRepository.save(coffeeMapper.requestDtoToEntity(coffeeRequestDto));
        return coffeeMapper.entityToResponseDto(coffee);

    }

}
