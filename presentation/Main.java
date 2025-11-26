package presentation;

import business.facade.LibraryFacade;
import business.factoryMethod.*;
import business.strategy.*;
import data.BookRepository;

public class Main {
  public static void main(String[] args) {
    BookRepository repo = new BookRepository();
    LibraryFacade library = new LibraryFacade(repo);

    library.addBook(new ScienceBookCreator(), "Física Cuántica", "Hawking");
    library.addBook(new LiteratureBookCreator(), "Don Quijote", "Cervantes");

    library.setSearchStrategy(new TitleSearchStrategy());
    library.searchBook("Don");

    library.setSearchStrategy(new AuthorSearchStrategy());
    library.searchBook("Hawking");

    library.searchBook("Albert");
  }
}
