package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("SFG publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");
       publisherRepository.save(publisher);

        Author eric = new Author("eric","evans");
        Book ddd = new Book("domain driven design","231131");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setBookPublisher(publisher);
        publisher.getPublishedBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author bob = new Author("Robert","Martin");
        Book cc = new Book("Clean Code","1231312");
        bob.getBooks().add(cc);
        cc.getAuthors().add(bob);
        cc.setBookPublisher(publisher);
        publisher.getPublishedBooks().add(cc);

        authorRepository.save(bob);
        bookRepository.save(cc);
        publisherRepository.save(publisher);

        System.out.println("started in bootstrap");
        System.out.println("numbeer of books: "  + bookRepository.count() );
        System.out.println("publisher number if books" + publisher.getPublishedBooks().size());
    }
}
