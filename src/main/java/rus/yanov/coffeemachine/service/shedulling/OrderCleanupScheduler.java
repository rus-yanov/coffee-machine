package rus.yanov.coffeemachine.service.shedulling;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rus.yanov.coffeemachine.service.OrderService;

@RequiredArgsConstructor
@Component
public class OrderCleanupScheduler {

    private final OrderService orderService;

    /**
     * Шедулер для ежесуточного удаления старой статистики по заказам
     */
    @Scheduled(cron = "${timeSheets.cron}")
    public void cleanupOldOrders() {

        orderService.deleteOldOrders();
    }
}
