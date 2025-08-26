package br.com.bookhub.repository;

import br.com.bookhub.entity.Book;
import br.com.bookhub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategories(List<Category> categories);
}
