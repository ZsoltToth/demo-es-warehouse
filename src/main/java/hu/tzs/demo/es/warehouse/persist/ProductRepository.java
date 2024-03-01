package hu.tzs.demo.es.warehouse.persist;

import hu.tzs.demo.es.warehouse.persist.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
