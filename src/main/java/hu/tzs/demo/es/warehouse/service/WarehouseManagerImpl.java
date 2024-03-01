package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.persist.WarehouseRepository;
import hu.tzs.demo.es.warehouse.persist.entity.WarehouseEntity;
import hu.tzs.demo.es.warehouse.service.exception.WarehouseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseManagerImpl implements WarehouseManager {

    private final WarehouseRepository warehouseRepository;

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

    private Warehouse entity2model(WarehouseEntity entity) {
        return new Warehouse(entity.getId(), entity.getName());
    }
}
