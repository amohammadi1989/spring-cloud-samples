package com.main.repository;
import com.main.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 31 Oct, 2021
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
