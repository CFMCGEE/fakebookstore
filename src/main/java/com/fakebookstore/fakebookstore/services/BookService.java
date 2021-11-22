package com.fakebookstore.fakebookstore.services;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.models.Category;
import com.fakebookstore.fakebookstore.repositories.BookRepository;
import com.fakebookstore.fakebookstore.repositories.CategoryRepository;
import com.fakebookstore.fakebookstore.services.booksuccessmethods.*;
import com.fakebookstore.fakebookstore.services.booksuccessmethods.exception.BookGetAllException;
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

//Business Logic ONLY
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

    public Object getAllBooks() {

        if (bookRepository.findAll().isEmpty()) {
            throw new BookGetAllException();
        }

        ReadSuccess readSuccess = new ReadSuccess("All books successfully found.", bookRepository.findAll());

        logger.info("All books successfully found.");
        return readSuccess;

    }

    public Object getBook(Long id) {

        SingleReadSuccess singleReadSuccess = new SingleReadSuccess("Book successfully found.",
                bookRepository.findById(id).orElse(null));

        logger.info("Book successfully found.");
        return singleReadSuccess;

    }

    public Object getAllBooksByCategory(Long id) {

        ReadSuccess readSuccess = new ReadSuccess("All books by category successfully found.", bookRepository.findAllByCategoryId(id));

        logger.info("All books by category successfully found.");
        return readSuccess;

    }

    public Object createBook(Book book) {

        Category categories = categoryRepository.findById(book.getCategory().getId()).get();

        book.setCategory(categories);

        Book new_book = bookRepository.save(book);

        CreateSuccess createSuccess = new CreateSuccess("Book successfully created", new_book);

        logger.info("Book successfully created");
        return createSuccess;

    }

    public Object updateBook(Long id, Book book) {

        UpdateSuccess updateSuccess = new UpdateSuccess("Book successfully updated.", bookRepository.save(book));

        logger.info("Book successfully updated.");
        return updateSuccess;

    }

    public Object deleteBook(Long id) {

        bookRepository.deleteById(id);

        DeleteSuccess deleteSuccess = new DeleteSuccess("Book successfully removed.");

        logger.info("Book successfully removed.");
        return deleteSuccess;

    }

}
