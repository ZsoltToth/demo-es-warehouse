package hu.tzs.demo.es.warehouse.persist;

import hu.tzs.demo.es.warehouse.persist.entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<StorageEntity, StorageEntity.StorageKey> {

    Collection<StorageEntity> findByIdWarehouseId(UUID warehouseId);
}
