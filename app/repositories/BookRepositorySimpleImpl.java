package repositories;

import models.Book;

import java.util.*;

public class BookRepositorySimpleImpl implements BookRepository {
    private static Set<Book> books = new HashSet<>();

    static {
        books.add(new Book(1, "Effective Java", 27.79, "Joshua Bloch"));
        books.add(new Book(2, "Head First Design Patterns", 36.36, "Eric Freeman"));
        books.add(new Book(3, "Java Concurrency in Practice", 15.87, "Brian Goetz"));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Book findById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book save(Book book) {
        int bookId = book.getId();
        if (Objects.nonNull(bookId)) {
            deleteById(bookId);
        }

        books.add(book);
        return book;
    }

    @Override
    public void deleteById(int id) {
        Book found = findById(id);
        if (Objects.nonNull(found)) {
            books.remove(found);
        }
    }
}
