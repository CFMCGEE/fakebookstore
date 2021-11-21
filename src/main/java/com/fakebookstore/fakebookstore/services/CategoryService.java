package com.fakebookstore.fakebookstore.services;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.models.Category;
import com.fakebookstore.fakebookstore.repositories.CategoryRepository;
import com.fakebookstore.fakebookstore.services.categorysuccessmethods.*;
import org.hibernate.sql.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Object> getAllCategories() {

        List<Category> listOfCategories = categoryRepository.findAll();

        ReadSuccess readSuccess = new ReadSuccess("All categories successfully found.", listOfCategories);

        logger.info("All categories successfully found.");
        return new ResponseEntity<>(readSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> getCategory(Long id) {

        SingleReadSuccess singleReadSuccess = new SingleReadSuccess("Category successfully found.",
                categoryRepository.findById(id).orElse(null));

        logger.info("Category successfully found.");
        return new ResponseEntity<>(singleReadSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> findCategoryById(Long id) {

        SingleReadSuccess singleReadSuccess = new SingleReadSuccess("Book's Category Successfully Found.", categoryRepository.findByBookId(id));

        logger.info("Book's Category Successfully Found.");
        return new ResponseEntity<>(singleReadSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> search(String name) {

        ReadSuccess readSuccess = new ReadSuccess("Successfully found what was searched.", categoryRepository.findByName(name));

        logger.info("Successfully found what was searched.");
        return new ResponseEntity<>(readSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> createCategory(Category category) {

        Category new_category = categoryRepository.save(category);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(new_category.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        CreateSuccess createSuccess = new CreateSuccess("Category successfully created", new_category);

        logger.info("Category successfully created");
        return new ResponseEntity<>(createSuccess, responseHeaders, HttpStatus.CREATED);

    }

    public ResponseEntity<Object> updateCategory(Long id, Category category) {

        UpdateSuccess updateSuccess = new UpdateSuccess("Category successfully updated.", categoryRepository.save(category));

        logger.info("Category successfully updated.");
        return new ResponseEntity<>(updateSuccess, HttpStatus.OK);

    }

    public ResponseEntity<Object> deleteCategory(Long id) {

        categoryRepository.deleteById(id);

        DeleteSuccess deleteSuccess = new DeleteSuccess("Category successfully removed.");

        logger.info("Category successfully removed.");
        return new ResponseEntity<>(deleteSuccess, HttpStatus.ACCEPTED);

    }

}
