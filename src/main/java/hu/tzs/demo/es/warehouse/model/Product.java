package hu.tzs.demo.es.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Product {

    private UUID id;

    private String name;
}
