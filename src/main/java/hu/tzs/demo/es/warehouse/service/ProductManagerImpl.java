package hu.tzs.demo.es.warehouse.service;

import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.persist.ProductRepository;
import hu.tzs.demo.es.warehouse.persist.entity.ProductEntity;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManagerImpl implements ProductManager {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(String name) {
        ProductEntity entity = ProductEntity.builder().name(name).build();
        entity = productRepository.save(entity);
        return this.entity2model(entity);
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productRepository.findAll().stream().map(this::entity2model).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        Optional<ProductEntity> queryResult = productRepository.findById(id);
        if (queryResult.isEmpty()) {
            throw new ProductNotFoundException(String.format("Product (%s) Not Found!", id));
        }
        return this.entity2model(queryResult.get());
    }

    private Product entity2model(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName());
    }
}
