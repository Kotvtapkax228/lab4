package com.uni.lab4;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends PagingAndSortingRepository<BookItem, BookID> {
    List<BookItem> findByAuthorAndTitleAndGenre(String author, String title, String gennre);
    List<BookItem> findByAuthorAndTitle(String author, String title);
    List<BookItem> findByAuthor(String author);
    List<BookItem> findByTitle(String title);
    Page<BookItem> findAll(Pageable pageable);

}
