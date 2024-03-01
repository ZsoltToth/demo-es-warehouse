package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface ProductManager {

    Product createProduct(String name);

    Collection<Product> getAllProducts();

    Product getProductById(UUID id) throws ProductNotFoundException;
}
