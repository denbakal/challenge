package ua.challenge.controller.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.challenge.sandbox.cache.BookRepository;

/**
 * Created by d.bakal on 4/10/2016.
 */
@RestController
@RequestMapping("/sandbox")
public class SandboxController {
    private final static Logger log = LoggerFactory.getLogger(SandboxController.class);

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/book")
    public void fetchBooks() {
        log.info(".... Fetching books");
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    }

    @RequestMapping("/book/cache")
    public void fetchBooksFromCache() {
        log.info(".... Fetching books");
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    }
}
