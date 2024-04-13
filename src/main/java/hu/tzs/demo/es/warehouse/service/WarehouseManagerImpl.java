package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.persist.StorageRepository;
import hu.tzs.demo.es.warehouse.persist.WarehouseRepository;
import hu.tzs.demo.es.warehouse.persist.entity.WarehouseEntity;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springdoc.core.providers.HateoasHalProvider;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Service
@RequiredArgsConstructor
public class WarehouseManagerImpl implements WarehouseManager {

    private final ProductManager productManager;
    private final WarehouseRepository warehouseRepository;
    private final StorageRepository storageRepository;

    @Override
    public Warehouse createWarehouse(String name) {
        WarehouseEntity entity = warehouseRepository.save(
                WarehouseEntity.builder().name(name).build()
        );
        return this.entity2model(entity);
    }

    @Override
    public Collection<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll().stream().map(this::entity2model).collect(Collectors.toList());
    }

    @Override
    public Warehouse getWarehouseById(UUID id) throws WarehouseNotFoundException {
        Optional<WarehouseEntity> queryResult = warehouseRepository.findById(id);
        if (queryResult.isEmpty()) {
            throw new WarehouseNotFoundException(String.format("Warehouse (%s) was not found!", id));
        }
        return this.entity2model(queryResult.get());
    }

    @Override
    public Map<Product, Long> getStorageOfWarehouse(UUID warehouseId) {
        Map<Product, Long> result = new HashMap<>();
        storageRepository.findByIdWarehouseId(warehouseId).stream().forEach(
                storageEntity -> {
                    try {
                        Product product = productManager.getProductById(storageEntity.getId().getProductId());
                        Long count = storageEntity.getCount();
                        result.put(product, count);
                    } catch (ProductNotFoundException e) {
                        log.info(String.format("Product (%s) was not found during Storage Fetching!", storageEntity.getId().getProductId()));
                        throw new RuntimeException(e);
                    }
                }
        );
        return result;
    }

    private Warehouse entity2model(WarehouseEntity entity) {
        return new Warehouse(entity.getId(), entity.getName());
    }
}
