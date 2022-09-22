package tech.dimitar.liquibase.example.database.db2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.dimitar.liquibase.example.database.db2.entities.BookEntity;

import java.util.List;

@Repository
public interface BookEntityRepository extends CrudRepository<BookEntity, String> {
    List<BookEntity> findAll();
}
