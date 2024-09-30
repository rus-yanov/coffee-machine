package rus.yanov.coffeemachine.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Schema(description = "DTO для обновления информации о заказах")
public class OrderRequestDto {

    @Schema(description = "Комментарий к заказу")
    @JsonProperty("comment")
    private String comment;

    @Schema(description = "Список напитков в заказе")
    @JsonProperty("drinks")
    private List<UUID> coffeeIds;

}
