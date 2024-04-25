package hu.tzs.demo.es.warehouse.model;

import java.time.Instant;
import java.util.UUID;

public class StorageEvent {

    private UUID eventId;
    private Instant timestamp;
    private Warehouse warehouse;
    private Product product;
    private StorageEventType eventType;
    private int amount;

}
