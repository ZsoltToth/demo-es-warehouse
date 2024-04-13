package hu.tzs.demo.es.warehouse.controller;

import hu.tzs.demo.es.warehouse.controller.dto.StorageItemDto;
import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.service.StorageManager;
import hu.tzs.demo.es.warehouse.service.WarehouseManager;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("/warehouses/{warehouseId}/products")
@RequiredArgsConstructor
public class StorageController {

    private final WarehouseManager warehouseManager;
    private final StorageManager storageManager;

    @GetMapping
    public Collection<StorageItemDto> fetchAllStorageOfWarehouse(@PathVariable UUID warehouseId) {
        Map<Product, Long> storage = storageManager.getStorageOfWarehouse(warehouseId);
        return storage.entrySet()
                .stream()
                .map(
                        entry -> new StorageItemDto(
                                entry.getKey().getId(),
                                entry.getKey().getName(),
                                entry.getValue()
                        )
                ).collect(Collectors.toList());
    }

    @PostMapping
    public Collection<StorageItemDto> recordStorageItemsForWarehouse(@PathVariable UUID warehouseId, @RequestBody Collection<StorageItemDto> storedProducts) {
        Map<Product, Long> depositedProducts = new HashMap<>();
        storedProducts.stream().forEach(dto -> {
            depositedProducts.put(
                    new Product(dto.id(), dto.name()),
                    dto.count()
            );
        });
        Map<Product, Long> totalStorage = new HashMap<>();
        try {
            totalStorage = storageManager.deposit(warehouseManager.getWarehouseById(warehouseId), depositedProducts);
        } catch (WarehouseNotFoundException e) {
            throw new RuntimeException(e);
        }
        return totalStorage.entrySet()
                .stream()
                .map(
                        entry -> new StorageItemDto(entry.getKey().getId(), entry.getKey().getName(), entry.getValue()
                        )
                ).collect(Collectors.toList());
    }
}
