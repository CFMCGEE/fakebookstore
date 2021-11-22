package com.fakebookstore.fakebookstore.controllers;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.services.BookService;
import com.fakebookstore.fakebookstore.services.booksuccessmethods.ReadSuccess;
import com.fakebookstore.fakebookstore.services.booksuccessmethods.SingleReadSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//Request Logic ONLY
@RestController
@RequestMapping("/bookstore")
public class BookController {

        @Autowired
        private BookService bookService;

        @PostMapping
        public ResponseEntity<Object> makeBook(@Valid @RequestBody Book book) {

                URI newBookUri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(book.getId())
                        .toUri();

                return ResponseEntity.created(newBookUri).body(bookService.createBook(book));

        }

        @GetMapping
        public ResponseEntity<Object> retrieveAllBooks() {
                return ResponseEntity.ok(bookService.getAllBooks());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> retrieveABook(@PathVariable Long id) {
                return ResponseEntity.ok(bookService.getBook(id));
        }

        @GetMapping("/category/{id}/books")
        public ResponseEntity<Object> retrieveAllBooksByCategory(@PathVariable Long id) {
                return ResponseEntity.ok(bookService.getAllBooksByCategory(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> editBook(@PathVariable Long id, @Valid @RequestBody Book book) {
                return ResponseEntity.ok(bookService.updateBook(id, book));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> removeBook(@PathVariable Long id) {
                return ResponseEntity.accepted().body(bookService.deleteBook(id));
        }

}
