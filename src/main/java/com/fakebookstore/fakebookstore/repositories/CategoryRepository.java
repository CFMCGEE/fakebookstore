package com.fakebookstore.fakebookstore.repositories;

import com.fakebookstore.fakebookstore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

        @Query("SELECT c FROM Category c JOIN c.books b WHERE b.name LIKE %?1%")
        List<Category> findByName(String name);

        @Query("SELECT c FROM Category c JOIN c.books b WHERE b.id = ?1")
        Category findByBookId(Long id);

}
