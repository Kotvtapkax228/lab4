package com.uni.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/books", produces = {"application/json"})
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<BookItem> saveBook(@RequestBody BookItem book) throws BookNotCreatedException, BookNotFoundException {
        book = bookRepository.save(book);
        if(book == null){
            throw new BookNotCreatedException("book failed to create ...");
        }
        return EntityModel.of(book);
    }
    @GetMapping
    public CollectionModel<BookItem> getAll() throws BookNotFoundException {
        Iterable<BookItem> books = bookRepository.findAll();
        if (!books.iterator().hasNext())
            throw  new BookNotFoundException("any books was found ...");

        return CollectionModel.of(books);
    }
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/p")
    public CollectionModel<BookItem> getBooks() throws BookNotFoundException {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Iterable<BookItem> books = bookRepository.findAll(firstPageWithTwoElements);
        if(!books.iterator().hasNext()) {
            throw  new BookNotFoundException("books with {author} not found ...");
        }
        return CollectionModel.of(books);
    }
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{author}")
    public CollectionModel<BookItem> getBooksByAuthor(@PathVariable String author) throws BookNotFoundException {
        Iterable<BookItem> books = bookRepository.findByAuthor(author);
        if(!books.iterator().hasNext()) {
            throw  new BookNotFoundException("books with {author} not found ...");
        }
        return CollectionModel.of(books);
    }
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{title}")
    public CollectionModel<BookItem> getBooksByTitle(@PathVariable String title) throws BookNotFoundException {
        Iterable<BookItem> books = bookRepository.findByTitle(title);
        if(!books.iterator().hasNext()) {
            throw  new BookNotFoundException("books with {title} not found ...");
        }
        return CollectionModel.of(books);
    }
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{genre}")
    public CollectionModel<BookItem> getBooksByGenre(@PathVariable String genre) throws BookNotFoundException {
        Iterable<BookItem> books = bookRepository.findByAuthor(genre);
        if(!books.iterator().hasNext()) {
            throw  new BookNotFoundException("books with {genre} not found ...");
        }
        return CollectionModel.of(books);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookItem putBook(
            @RequestBody BookItem book) {

        return bookRepository.save(book);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@RequestBody BookItem book) {
        try {
            bookRepository.delete(book);
        } catch (EmptyResultDataAccessException e) {}
    }
}
