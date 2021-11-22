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

//Business Logic ONLY
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Object getAllCategories() {

        ReadSuccess readSuccess = new ReadSuccess("All categories successfully found.", categoryRepository.findAll());

        logger.info("All categories successfully found.");
        return readSuccess;

    }

    public Object getCategory(Long id) {

        SingleReadSuccess singleReadSuccess = new SingleReadSuccess("Category successfully found.",
                categoryRepository.findById(id).orElse(null));

        logger.info("Category successfully found.");
        return singleReadSuccess;

    }

    public Object findCategoryById(Long id) {

        SingleReadSuccess singleReadSuccess = new SingleReadSuccess("Book's Category Successfully Found.", categoryRepository.findByBookId(id));

        logger.info("Book's Category Successfully Found.");
        return singleReadSuccess;

    }

    public Object search(String name) {

        ReadSuccess readSuccess = new ReadSuccess("Successfully found what was searched.", categoryRepository.findByName(name));

        logger.info("Successfully found what was searched.");
        return readSuccess;

    }

    public Object createCategory(Category category) {

        Category new_category = categoryRepository.save(category);

        CreateSuccess createSuccess = new CreateSuccess("Category successfully created", new_category);

        logger.info("Category successfully created");
        return createSuccess;

    }

    public Object updateCategory(Long id, Category category) {

        UpdateSuccess updateSuccess = new UpdateSuccess("Category successfully updated.", categoryRepository.save(category));

        logger.info("Category successfully updated.");
        return updateSuccess;

    }

    public Object deleteCategory(Long id) {

        categoryRepository.deleteById(id);

        DeleteSuccess deleteSuccess = new DeleteSuccess("Category successfully removed.");

        logger.info("Category successfully removed.");
        return deleteSuccess;

    }

}
