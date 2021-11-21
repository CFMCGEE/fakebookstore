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

        @PostMapping("/")
        public ResponseEntity<?> makeBook(@Valid @RequestBody Book book) {
                return bookService.createBook(book);
        }

        @GetMapping("/")
        public ResponseEntity<?> retrieveAllBooks() {
                return bookService.getAllBooks();
        }

        @GetMapping("/search")
        public ResponseEntity<Object> getByBookNames(@RequestParam("name") String name) {
                return bookService.searchByBookName(name);
        }

//        @PostMapping("/search/{name}")
//        public ResponseEntity<Object> getByBookNames(@PathVariable String name) {
//                return bookService.searchByBookName(name);
//        }

//        @GetMapping("/search")
//        public String searchPage(@RequestParam("searchString") String searchString){
//                return bookService.searchPage(searchString);
//        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> retrieveABook(@PathVariable Long id) {
                return bookService.getBook(id);
        }

        @GetMapping("/{id}/category")
        public ResponseEntity<Object> retrieveBookCategoryByBookID(@PathVariable Long id) {
                return bookService.getBookCategoryByBookID(id);
        }

        @GetMapping("/category/{id}/books")
        public ResponseEntity<Object> retrieveAllBooksByCategory(@PathVariable Long id) {
                return bookService.getAllBooksByCategory(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> editBook(@PathVariable Long id, @Valid @RequestBody Book book) {
                return bookService.updateBook(id, book);
        }

//        @PutMapping("/{bookId}/category/{categoryId}")
//        public ResponseEntity<Object> assignBookToCategory(@PathVariable Long bookId, @PathVariable Long categoryId) {
//                return bookService.placeBookInCategory(bookId, categoryId);
//        }

        @DeleteMapping("/{bookId}")
        public ResponseEntity<Object> removeBook(@PathVariable Long bookId) {
                return bookService.deleteBook(bookId);
        }

}
