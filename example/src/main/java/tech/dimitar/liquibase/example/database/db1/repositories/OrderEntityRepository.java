package tech.dimitar.liquibase.example.database.db1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.dimitar.liquibase.example.database.db1.entities.OrderEntity;

import java.util.List;

@Repository
public interface OrderEntityRepository extends CrudRepository<OrderEntity, String > {
    List<OrderEntity> findAll();
}