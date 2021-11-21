package com.fakebookstore.fakebookstore.services;

import com.fakebookstore.fakebookstore.models.Book;
import com.fakebookstore.fakebookstore.models.Category;
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
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<?> getAllCategories() {

        List<Category> listOfCategories = categoryRepository.findAll();

        logger.info("All categories successfully found.");
        return new ResponseEntity<>(listOfCategories, HttpStatus.OK);

    }

    public ResponseEntity<Object> getCategory(Long id) {
        logger.info("Category successfully found.");
        return new ResponseEntity<>(categoryRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    public ResponseEntity<?> createCategory(Category category) {

        logger.info("Category successfully created");

        Category new_category = categoryRepository.save(category);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(new_category.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    public ResponseEntity<Object> updateCategory(Long id, Category category) {
        logger.info("Category successfully updated.");
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteCategory(Long id) {

        logger.info("Category successfully removed.");

        categoryRepository.deleteById(id);

        deletesuccess successful = new deletesuccess(HttpStatus.ACCEPTED.value(), "Category successfully removed.");

        return new ResponseEntity<>(successful, HttpStatus.ACCEPTED);
    }

}
