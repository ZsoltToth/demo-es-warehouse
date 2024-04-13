package hu.tzs.demo.es.warehouse.controller.dto;

import java.util.UUID;

public record StorageItemDto(UUID id, String name, Long count) {
}
