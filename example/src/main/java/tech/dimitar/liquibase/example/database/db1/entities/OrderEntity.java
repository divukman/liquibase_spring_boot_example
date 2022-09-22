package tech.dimitar.liquibase.example.database.db1.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity {

    @Id
    private String orderId;

    private String productId;

    private String orderStatus;

}