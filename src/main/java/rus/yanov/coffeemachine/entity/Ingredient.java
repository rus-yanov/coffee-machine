package rus.yanov.coffeemachine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(fluent = true)
public class Ingredient {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "expense", nullable = false)
    private Integer expense;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "quantity_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuantityStatus quantityStatus;

    public enum Type {
        GRAIN,
        WATTER,
        MILK,
        SYRUP,
        SUGAR,
        CINNAMON
    }

    public enum QuantityStatus {
        FULL,
        NOT_FULL,
        EMPTY
    }

}
