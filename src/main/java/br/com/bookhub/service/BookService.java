package br.com.bookhub.service;

import br.com.bookhub.exception.ResourceNotFoundException;
import br.com.bookhub.entity.Book;
import br.com.bookhub.entity.Category;
import br.com.bookhub.entity.Provider;
import br.com.bookhub.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final ProviderService providerService;

    public Book save(Book book) {
        book.setCategories(this.findCategories(book.getCategories()));
        book.setProviders(this.findProviders(book.getProviders()));
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> update (Long id, Book updateBook) {
        Optional<Book> optBook =bookRepository.findById(id);

        if(optBook.isPresent()) {
            List<Category> categories = this.findCategories(updateBook.getCategories());
            List<Provider> providers = this.findProviders(updateBook.getProviders());

            Book book = optBook.get();
            book.setTitle(updateBook.getTitle());
            book.setDescription(updateBook.getDescription());
            book.setAuthor(updateBook.getAuthor());
            book.setRating(updateBook.getRating());

            book.getCategories().clear();
            book.getCategories().addAll(categories);

            book.getProviders().clear();
            book.getProviders().addAll(providers);

            bookRepository.save(book);

            return Optional.of(book);
        }

        return Optional.empty();
    }

    public List<Book> findByCategory(Long categoryId) {
        return bookRepository.findByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void delete(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        bookRepository.delete(book.get());
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoryFound = new ArrayList<>();
        categories.forEach(category ->
                categoryService.findById(category.getId()).ifPresent(categoryFound::add)
        );
        return categoryFound;
    }

    private List<Provider> findProviders(List<Provider> providers) {
        List<Provider> providerFound = new ArrayList<>();
        providers.forEach(provider -> {
            Optional<Provider> found = providerService.findById(provider.getId());
            found.ifPresent(providerFound::add);
        });
        return providerFound;
    }
}
