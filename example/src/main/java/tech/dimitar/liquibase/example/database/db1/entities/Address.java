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
public class Address {
    @Id
    private String addressId;

    private String country;
    private String zip;
    private String address;

}
