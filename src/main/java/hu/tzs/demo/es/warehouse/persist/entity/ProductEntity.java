package hu.tzs.demo.es.warehouse.persist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    private String name;
}
