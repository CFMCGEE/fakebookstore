package com.fakebookstore.fakebookstore.repositories;

import com.fakebookstore.fakebookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//
//    @Query("SELECT b FROM Book b WHERE b.CATEGORY_ID = ?1")// WHERE b.category_id = ?1
//    List<Book> findAllByCategory(Long categoryId);
    //List<Book> findAllByCategory(@Param("categoryId") Long categoryId);

    @Query("SELECT b FROM Book b WHERE b.name LIKE UPPER('%?1%')")
    List<Book> search(String name);

//    @Query(value = "SELECT NAME FROM BOOK WHERE NAME LIKE '%java%'", nativeQuery = true)
//    List<Book> search(String name);

   // @Query(value = "SELECT * FROM BOOK WHERE CATEGORY_ID = ?1", nativeQuery = true)
    List<Book> findAllByCategoryId(Long categoryId);

    //@Query("SELECT b FROM Book b WHERE b.id = ?1") findByBookId
    Book findCategoryById(Long id);

//    @Query(value = "SELECT * FROM Book WHERE book_id = ?1", nativeQuery = true)
//    Book getBookCategoryByID(Long bookId);

//    @Query(value = "SELECT CATEGORY_ID FROM BOOK WHERE BOOK_ID = ?1", nativeQuery = true)
//    Book getBookCategoryByID(Long bookId);

}
