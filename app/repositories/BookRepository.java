package repositories;

import models.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();
    Book findById(int id);
    Book save(Book book);
    void deleteById(int id);
}
