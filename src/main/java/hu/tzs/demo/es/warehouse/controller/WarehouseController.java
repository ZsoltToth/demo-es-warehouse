package hu.tzs.demo.es.warehouse.controller;

import hu.tzs.demo.es.warehouse.controller.dto.WarehouseDto;
import hu.tzs.demo.es.warehouse.controller.dto.WarehouseRecordRequestDto;
import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.service.WarehouseManager;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseManager warehouseManager;

    @GetMapping
    Collection<WarehouseDto> fetchAllWarehouses() {
        return warehouseManager.getAllWarehouses().stream().map(this::model2dto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    WarehouseDto fetchWarehouseById(@PathVariable UUID id) {
        try {
            Warehouse warehouse = warehouseManager.getWarehouseById(id);
            return this.model2dto(warehouse);
        } catch (WarehouseNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    WarehouseDto createWarehouse(@RequestBody WarehouseRecordRequestDto requestDto) {
        Warehouse warehouse = warehouseManager.createWarehouse(requestDto.name());
        return this.model2dto(warehouse);
    }

    private WarehouseDto model2dto(Warehouse warehouse) {
        return new WarehouseDto(warehouse.getId(), warehouse.getName());
    }
}
