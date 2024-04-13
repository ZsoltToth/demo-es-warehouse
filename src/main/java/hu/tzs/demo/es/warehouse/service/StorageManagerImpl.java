package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.persist.StorageRepository;
import hu.tzs.demo.es.warehouse.persist.entity.StorageEntity;
import hu.tzs.demo.es.warehouse.service.exception.AmountNotAvailableException;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class StorageManagerImpl implements StorageManager {

    private final StorageRepository storageRepository;
    private final ProductManager productManager;
    private final WarehouseManager warehouseManager;

    @Override
    public Map<Product, Long> getStorageOfWarehouse(UUID warehouseId) {
        Map<Product, Long> result = new HashMap<>();
        storageRepository.findByIdWarehouseId(warehouseId).stream().forEach(
                storageEntity -> {
                    try {
                        result.put(
                                productManager.getProductById(storageEntity.getId().getProductId()),
                                storageEntity.getCount()
                        );
                    } catch (ProductNotFoundException e) {
                        log.info(String.format("Unknown product (%s) in warehouse (%s)", storageEntity.getId().getProductId(), storageEntity.getId().getWarehouseId()));
                    }
                }
        );
        return result;
    }

    @Override
    public Long deposit(Warehouse warehouse, Product product, Long amount) throws WarehouseNotFoundException, ProductNotFoundException {
        warehouseManager.getWarehouseById(warehouse.getId());
        productManager.getProductById(product.getId());
        StorageEntity.StorageKey storageKey = new StorageEntity.StorageKey(warehouse.getId(), product.getId());
        Optional<StorageEntity> entity = storageRepository.findById(storageKey);
        StorageEntity result;
        if (entity.isEmpty()) {
            result = storageRepository.save(
                    StorageEntity.builder()
                            .id(storageKey)
                            .count(amount)
                            .build()
            );
        }
        else{
            entity.get().setCount(entity.get().getCount() + amount);
            result = storageRepository.save(entity.get());
        }
        return result.getCount();
    }

    @Override
    public Map<Product, Long> deposit(Warehouse warehouse, Map<Product, Long> depositedItems){
        Map<Product, Long> result = new HashMap<>();
        depositedItems.entrySet().forEach(entry -> {
            try {
                Long updatedAmount = this.deposit(warehouse, entry.getKey(), entry.getValue());
                result.put(productManager.getProductById(entry.getKey().getId()), updatedAmount);
            } catch (WarehouseNotFoundException e) {
                log.info(String.format("Warehouse (%s) was not found!", warehouse.getId()));
            } catch (ProductNotFoundException e) {
                log.info(String.format("Product (%s) was not found!",entry.getKey().getId()));
            }
        });
        return result;
    }

    @Override
    public Long withdraw(Warehouse warehouse, Product product, Long amount) throws WarehouseNotFoundException, ProductNotFoundException, AmountNotAvailableException {
        return null;
    }
}
