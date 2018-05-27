package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.BookRepository;
import views.html.books.create;
import views.html.books.edit;
import views.html.books.index;
import views.html.books.show;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

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
        return ok(index.render(books));
    }

    public Result getBookById(int bookId) {
        Book book = bookRepository.findById(bookId);
        if (Objects.isNull(book)) {
            return notFound(String.format("Book with id=%d was not found.", bookId));
        }
        return ok(show.render(book));
    }

    public Result createBook() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        bookRepository.save(book);
        return redirect(routes.BooksController.getAll());
    }

    public Result saveBook(int bookId) {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        book.setId(bookId);
        bookRepository.save(book);
        return redirect(routes.BooksController.getAll());
    }

    public Result deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
        return redirect(routes.BooksController.getAll());
    }

    public Result createBookForm() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
    }

    public Result editBookForm(int bookId) {
        Book book = bookRepository.findById(bookId);

        if (Objects.isNull(book)) {
            return notFound(String.format("Book with id=%d was not found.", bookId));
        }

        Form<Book> bookForm = formFactory.form(Book.class)
                .fill(book);
        return ok(edit.render(bookForm, bookId));
    }
}
