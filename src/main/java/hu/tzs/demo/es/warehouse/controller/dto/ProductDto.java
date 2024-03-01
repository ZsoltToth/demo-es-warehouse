package hu.tzs.demo.es.warehouse.controller.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record ProductDto(UUID id, String name) {
}
