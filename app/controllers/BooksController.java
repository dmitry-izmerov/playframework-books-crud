package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.BookRepository;
import views.html.books.create;
import views.html.books.index;

import javax.inject.Inject;
import java.util.List;

public class BooksController extends Controller {

    private final BookRepository bookRepository;
    private final FormFactory formFactory;

    @Inject
    public BooksController(BookRepository bookRepository, FormFactory formFactory) {
        this.bookRepository = bookRepository;
        this.formFactory = formFactory;
    }

    public Result getAll() {
        List<Book> books = bookRepository.getAll();
        return ok(Json.toJson(books));
    }

    public Result indexForm() {
        List<Book> books = bookRepository.getAll();
        return ok(index.render(books));
    }

    public Result getBookById(int bookId) {
        return TODO;
    }

    public Result createBookForm() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
    }

    public Result createBook() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        bookRepository.save(book);
        return redirect(routes.BooksController.indexForm());
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
