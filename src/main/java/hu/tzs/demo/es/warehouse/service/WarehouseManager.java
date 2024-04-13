package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface WarehouseManager {

    Warehouse createWarehouse(String name);

    Collection<Warehouse> getAllWarehouses();

    Warehouse getWarehouseById(UUID id) throws WarehouseNotFoundException;

    Map<Product, Long> getStorageOfWarehouse(UUID warehouseId);
}
