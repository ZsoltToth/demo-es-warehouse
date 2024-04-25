package hu.tzs.demo.es.warehouse.persist;

import hu.tzs.demo.es.warehouse.persist.entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StorageEventRepository extends JpaRepository<StorageEntity, UUID> {
}
