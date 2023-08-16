package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile({"local", "default"})
@Component
public class DataInitialiser implements CommandLineRunner {
    private final BookRepository bookRepository;

    public DataInitialiser(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Book bookDDD = new Book("Domain Driven Design", "123", "RandomPublisher1");
        Book bookSIA = new Book("Spring in action", "123", "RandomPublisher2");

        Book savedDDD = bookRepository.save(bookDDD);
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("book id: " + book.getId());
            System.out.println("book title: " + book.getTitle());
        });

    }
}
