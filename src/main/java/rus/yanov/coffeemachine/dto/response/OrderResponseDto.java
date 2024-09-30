package rus.yanov.coffeemachine.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import rus.yanov.coffeemachine.utils.annotation.DefaultDateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Schema(description = "DTO для передачи полной информации о заказе")
public class OrderResponseDto {

    @Schema(description = "id заказа")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "Комментарий к заказу")
    @JsonProperty("comment")
    private String comment;

    @Schema(
            description = "Дата и время исполнения заказа в формате 'dd.MM.yyyy, HH:mm'",
            type = "string",
            example = "28.09.2024, 22:40"
    )
    @DefaultDateTimeFormat
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @Schema(description = "Список напитков в заказе")
    @JsonProperty("drinks")
    private List<CoffeeResponseDto> coffeeResponseDtoList;

}

