package br.com.bookhub.controller;

import br.com.bookhub.exception.ResourceNotFoundException;
import br.com.bookhub.controller.reponse.BookResponse;
import br.com.bookhub.controller.request.BookRequest;
import br.com.bookhub.entity.Book;
import br.com.bookhub.mapper.BookMapper;
import br.com.bookhub.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookhub/books")
@RequiredArgsConstructor
public class BookController implements BookDocs {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookRequest request) {
        Book savedBook = bookService.save(BookMapper.toBook(request));
        return ResponseEntity.ok(BookMapper.toBookResponse(savedBook));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok(bookService.findAll()
                .stream()
                .map(BookMapper::toBookResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(BookMapper::toBookResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return bookService.update(id, BookMapper.toBook(request))
                .map(book -> ResponseEntity.ok(BookMapper.toBookResponse(book)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/source")
    public ResponseEntity<List<BookResponse>> findByCategory(@RequestParam Long category) {
        return ResponseEntity.ok(bookService.findByCategory(category)
                .stream()
                .map(BookMapper::toBookResponse)
                .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}