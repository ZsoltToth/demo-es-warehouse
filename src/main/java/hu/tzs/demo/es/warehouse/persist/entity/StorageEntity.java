package hu.tzs.demo.es.warehouse.persist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="warehouse_product_storage")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageEntity {

    @EmbeddedId
    private StorageKey id;

    @Column(name="count")
    private Long count;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StorageKey implements Serializable{

        @Column(name="warehouse")
        private UUID warehouseId;

        @Column(name="product")
        private UUID productId;

    }
}
