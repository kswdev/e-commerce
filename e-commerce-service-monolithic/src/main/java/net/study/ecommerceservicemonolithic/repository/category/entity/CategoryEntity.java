package net.study.ecommerceservicemonolithic.repository.category.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "categories", schema = "ecommerce_monolithic")
@Entity
@Getter @Setter
public class CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
