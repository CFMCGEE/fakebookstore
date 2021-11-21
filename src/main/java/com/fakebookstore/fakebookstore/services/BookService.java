package com.fakebookstore.fakebookstore.services;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.models.Category;
import com.fakebookstore.fakebookstore.repositories.BookRepository;
import com.fakebookstore.fakebookstore.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> getAllBooks() {

        List<Book> listOfBooks = bookRepository.findAll();

        logger.info("All books successfully found.");
        return new ResponseEntity<>(listOfBooks, HttpStatus.OK);

    }

    public ResponseEntity<Object> getBook(Long id) {
        logger.info("Book successfully found.");
        return new ResponseEntity<>(bookRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    public ResponseEntity<?> createBook(Book book) {

        logger.info("Book successfully created");

        Category categories = categoryRepository.findById(book.getCategory().getId()).get(); //.orElse(null)

        book.setCategory(categories);

        Book new_book = bookRepository.save(book);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(new_book.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(new_book,  responseHeaders, HttpStatus.CREATED);

    }

//    public ResponseEntity<Object> createBook(Book book) {
//        logger.info("Book successfully created");
//        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
//    }

    public ResponseEntity<Object> updateBook(Long id, Book book) {
        logger.info("Book successfully updated.");
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteBook(Long bookId) {

        logger.info("Book successfully removed.");

        bookRepository.deleteById(bookId);

        deletesuccess successful= new deletesuccess(HttpStatus.ACCEPTED.value(), "Book successfully removed.");

        return new ResponseEntity<>(successful, HttpStatus.ACCEPTED);
    }

//    public ResponseEntity<Object> placeBookInCategory(Long bookId, Long categoryId) {
//
//        logger.info("Book successfully placed into Category");
//
//        Book book = bookRepository.findById(bookId).get();
//        Category category = categoryRepository.findById(categoryId).get();
//        book.assignBookToCategory(category);
//
//        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
//
//    }

//    public List<Book> searchByBookName(String name) {
//
//        List<Book> booksBySearch = bookRepository.search(name);
//
//        return booksBySearch;
//
//    }

    public ResponseEntity<Object> searchByBookName(String name) {

        try {
//        List<Book> booksBySearch = bookRepository.search(name);
            return new ResponseEntity<>(bookRepository.search(name), HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }

//    public String searchPage(String searchString) {
//        if(searchString != null){
//            try {
//                Iterable<Book> searchResult = bookRepository.search(searchString);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return new ResponseEntity<>(sear, HttpStatus.OK);
//    }

//        if (name != null) {
//            return bookRepository.search(name);
//        }
//        return bookRepository.findAll();
//    }
//
//    public List<Product> listAll(String keyword) {
//        if (keyword != null) {
//            return repo.search(keyword);
//        }
//        return repo.findAll();
//    }


    public ResponseEntity<Object> getAllBooksByCategory(Long id) {

        List<Book> booksByCategory = bookRepository.findAllByCategoryId(id);

        logger.info("All books by category successfully found.");
        return new ResponseEntity<>(booksByCategory, HttpStatus.OK);

    }

    public ResponseEntity<Object> getBookCategoryByBookID(Long id) {

        Book bookCategoryByID = bookRepository.findCategoryById(id);

        Book book = new Book();

//        for (int i = 0; i < books.toArray().length; i++) {
//                if (books.contains(bookId)) {
//                    books.get(i).getCategory();
//                    books.get(i).getCategory();
//                }
//        }

        logger.info("Book based on ID successfully found.");
        return new ResponseEntity<>(bookCategoryByID, HttpStatus.OK);

    }

}
