package hu.tzs.demo.es.warehouse.service.es;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.model.Warehouse;
import hu.tzs.demo.es.warehouse.persist.StorageEventRepository;
import hu.tzs.demo.es.warehouse.persist.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageProjectorImpl implements StorageProjector {

    private final StorageEventRepository storageEventRepository;

    @Override
    public Map<Product, Integer> calculateStorage(Instant timestamp, Warehouse warehouse) {
        return null;
    }

    @Override
    public Integer calculateStorageOfProduct(Instant timestamp, Warehouse warehouse, Product product) {
        return null;
    }
}
