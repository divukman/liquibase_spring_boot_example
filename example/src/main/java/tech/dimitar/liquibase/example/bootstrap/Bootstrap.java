package tech.dimitar.liquibase.example.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.dimitar.liquibase.example.database.db1.entities.OrderEntity;
import tech.dimitar.liquibase.example.database.db1.repositories.OrderEntityRepository;
import tech.dimitar.liquibase.example.database.db2.entities.BookEntity;
import tech.dimitar.liquibase.example.database.db2.repositories.BookEntityRepository;

import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {
    private final OrderEntityRepository orderEntityRepository;
    private final BookEntityRepository bookEntityRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("***************************************************************");
        log.info("Bootstrapping some data into the database...");

        // Bootstrap some data into first db
        if (orderEntityRepository.findAll().isEmpty()) {
            final ArrayList<OrderEntity> lstEntities = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final OrderEntity orderEntity = OrderEntity.builder()
                        .orderId(UUID.randomUUID().toString())
                        .productId(UUID.randomUUID().toString())
                        .orderStatus("ORDERED")
                        .build();

                lstEntities.add(orderEntity);
            }

            orderEntityRepository.saveAll(lstEntities);
        }

        // Bootstrap some data into second db
        if (bookEntityRepository.findAll().isEmpty()) {
            final ArrayList<BookEntity> lstBooks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final BookEntity bookEntity = BookEntity.builder()
                        .bookId(UUID.randomUUID().toString())
                        .name("Test Book NO: " + i)
                        .isbn("ISBN: " + i)
                        .build();
                lstBooks.add(bookEntity);
            }
            bookEntityRepository.saveAll(lstBooks);
        }

        log.info("Done...");
    }
}