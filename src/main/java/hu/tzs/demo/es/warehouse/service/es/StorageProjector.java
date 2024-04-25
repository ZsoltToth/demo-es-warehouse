package hu.tzs.demo.es.warehouse.service.es;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.model.Warehouse;

import java.time.Instant;
import java.util.Map;

public interface StorageProjector {

    Map<Product, Integer> calculateStorage(Instant timestamp, Warehouse warehouse);
     Integer calculateStorageOfProduct(Instant timestamp, Warehouse warehouse, Product product);
}
