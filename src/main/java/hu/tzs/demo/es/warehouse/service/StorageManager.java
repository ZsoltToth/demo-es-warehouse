package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.service.exception.AmountNotAvailableException;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;

import java.util.Map;
import java.util.UUID;

public interface StorageManager {

    Map<Product, Long> getStorageOfWarehouse(UUID warehouseId);

    Long deposit(Warehouse warehouse, Product product, Long amount) throws WarehouseNotFoundException, ProductNotFoundException;
    Map<Product,Long> deposit(Warehouse warehouse, Map<Product, Long> depositedItems);
    Long withdraw(Warehouse warehouse, Product product, Long amount) throws WarehouseNotFoundException, ProductNotFoundException, AmountNotAvailableException;
}
