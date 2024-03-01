package hu.tzs.demo.es.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Warehouse {

    private UUID id;

    private String name;
}
