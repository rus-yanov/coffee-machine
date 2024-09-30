package rus.yanov.coffeemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rus.yanov.coffeemachine.entity.Coffee;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, UUID> {

    @Query("SELECT MAX(c.score) FROM Coffee c")
    Coffee findMostPopularCoffee();
}
