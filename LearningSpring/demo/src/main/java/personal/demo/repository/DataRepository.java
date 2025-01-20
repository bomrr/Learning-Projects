package personal.demo.repository;

import personal.demo.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * This interface is used to interact with the database.
 * It extends the JpaRepository interface, which provides CRUD operations.
 * 
 * CRUD is an acronym that stands for Create, Read, Update, and Delete.
 * These are the four basic functions of persistent storage.
 */

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}