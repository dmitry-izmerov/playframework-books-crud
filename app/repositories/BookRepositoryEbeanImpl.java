package repositories;

import models.Book;

import java.util.*;

public class BookRepositoryEbeanImpl implements BookRepository {

    @Override
    public List<Book> getAll() {
        return Book.finder.all();
    }

    @Override
    public Book findById(int id) {
        return Book.finder.byId(id);
    }

    @Override
    public Book save(Book book) {
        int bookId = book.getId();
        if (Objects.nonNull(bookId)) {
            deleteById(bookId);
        }

        Book.db().save(book);
        return book;
    }

    @Override
    public void deleteById(int id) {
        Book.finder.deleteById(id);
    }
}
