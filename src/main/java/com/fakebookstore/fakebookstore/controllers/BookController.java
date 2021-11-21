package com.fakebookstore.fakebookstore.controllers;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookController {

        @Autowired
        private BookService bookService;

        @PostMapping
        public ResponseEntity<Object> makeBook(@Valid @RequestBody Book book) {
                return bookService.createBook(book);
        }

        @GetMapping
        public ResponseEntity<Object> retrieveAllBooks() {
                return bookService.getAllBooks();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> retrieveABook(@PathVariable Long id) {
                return bookService.getBook(id);
        }

        @GetMapping("/category/{id}/books")
        public ResponseEntity<Object> retrieveAllBooksByCategory(@PathVariable Long id) {
                return bookService.getAllBooksByCategory(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> editBook(@PathVariable Long id, @Valid @RequestBody Book book) {
                return bookService.updateBook(id, book);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> removeBook(@PathVariable Long id) {
                return bookService.deleteBook(id);
        }

}
