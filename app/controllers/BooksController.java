package controllers;

import models.Book;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.BookRepository;
import views.html.books.index;

import javax.inject.Inject;
import java.util.List;

public class BooksController extends Controller {

    private BookRepository bookRepository;

    @Inject
    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Result getAll() {
        List<Book> books = bookRepository.getAll();
        return ok(index.render(books));
    }

    public Result getBookById(int bookId) {
        return TODO;
    }

    public Result createBook() {
        return TODO;
    }

    public Result saveBook(int bookId) {
        return TODO;
    }

    public Result deleteBook(int bookId) {
        return TODO;
    }

    public Result getBookDetails(int bookId) {
        return TODO;
    }
}
