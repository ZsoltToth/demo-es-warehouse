package hu.tzs.demo.es.warehouse.persist.entity;

import hu.tzs.demo.es.warehouse.model.StorageEventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="storage_events")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageEventEntity {

    @Id
    @Column(name="event_id")
    private UUID eventId;

    @Column(name="timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private WarehouseEntity warehouse;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column(name="event")
    private StorageEventType event;

    @Column(name="amount")
    private int amount;
}
