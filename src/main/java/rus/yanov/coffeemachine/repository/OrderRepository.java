package rus.yanov.coffeemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rus.yanov.coffeemachine.entity.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM Order o WHERE o.timestamp < :timestamp")
    List<Order> findOrdersOlderThan(LocalDateTime timestamp);
}
