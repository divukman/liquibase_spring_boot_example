package tech.dimitar.liquibase.example.database.db2.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity {
    @Id
    private String bookId;

    private String name;

    private String isbn;
}
