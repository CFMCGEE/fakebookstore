package com.fakebookstore.fakebookstore.services;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.models.Category;
import com.fakebookstore.fakebookstore.repositories.BookRepository;
import com.fakebookstore.fakebookstore.repositories.CategoryRepository;
import com.fakebookstore.fakebookstore.services.booksuccessmethods.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> getAllBooks() {

        List<Book> listOfBooks = bookRepository.findAll();

        ReadSuccess readSuccess = new ReadSuccess("All books successfully found.", listOfBooks);

        logger.info("All books successfully found.");
        return new ResponseEntity<>(readSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> getBook(Long id) {

        SingleReadSuccess singleReadSuccess = new SingleReadSuccess("Book successfully found.",
                bookRepository.findById(id).orElse(null));

        logger.info("Book successfully found.");
        return new ResponseEntity<>(singleReadSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> getAllBooksByCategory(Long id) {

        List<Book> booksByCategory = bookRepository.findAllByCategoryId(id);

        ReadSuccess readSuccess = new ReadSuccess("All books by category successfully found.", booksByCategory);

        logger.info("All books by category successfully found.");
        return new ResponseEntity<>(readSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> createBook(Book book) {

        Category categories = categoryRepository.findById(book.getCategory().getId()).get();

        book.setCategory(categories);

        Book new_book = bookRepository.save(book);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(new_book.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        CreateSuccess createSuccess = new CreateSuccess("Book successfully created", new_book);

        logger.info("Book successfully created");
        return new ResponseEntity<>(createSuccess,  responseHeaders, HttpStatus.CREATED);

    }

    public ResponseEntity<Object> updateBook(Long id, Book book) {

        UpdateSuccess updateSuccess = new UpdateSuccess("Book successfully updated.", bookRepository.save(book));

        logger.info("Book successfully updated.");
        return new ResponseEntity<>(updateSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> deleteBook(Long id) {

        bookRepository.deleteById(id);

        DeleteSuccess deleteSuccess = new DeleteSuccess("Book successfully removed.");

        logger.info("Book successfully removed.");
        return new ResponseEntity<>(deleteSuccess, HttpStatus.ACCEPTED);

    }

}
