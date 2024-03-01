package hu.tzs.demo.es.warehouse.controller;

import hu.tzs.demo.es.warehouse.controller.dto.ProductDto;
import hu.tzs.demo.es.warehouse.controller.dto.ProductRecordRequestDto;
import hu.tzs.demo.es.warehouse.model.Product;
import hu.tzs.demo.es.warehouse.persist.ProductRepository;
import hu.tzs.demo.es.warehouse.persist.entity.ProductEntity;
import hu.tzs.demo.es.warehouse.service.ProductManager;
import hu.tzs.demo.es.warehouse.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductManager productManager;

    @GetMapping
    public Collection<ProductDto> fetchAllProducts() {
        return productManager.getAllProducts().stream().map(this::model2dto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto fetchProductById(@PathVariable UUID id){
        try{
            Product product = productManager.getProductById(id);
            return this.model2dto(product);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductRecordRequestDto requestDto) {
        Product product = productManager.createProduct(requestDto.name());
        return this.model2dto(product);
    }

    private ProductDto model2dto(Product product){
        return ProductDto.builder().id(product.getId()).name(product.getName()).build();
    }

}
