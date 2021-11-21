package com.fakebookstore.fakebookstore.repositories;

import com.fakebookstore.fakebookstore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {



}
