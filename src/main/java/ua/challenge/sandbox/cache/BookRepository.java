package ua.challenge.sandbox.cache;

/**
 * Created by d.bakal on 4/10/2016.
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
}
