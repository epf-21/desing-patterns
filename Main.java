import facade.LibraryFacade;
import factoryMethod.LiteratureBookCreator;
import factoryMethod.ScienceBookCreator;
import strategy.AuthorSearchStrategy;
import strategy.TitleSearchStrategy;

public class Main {
  public static void main(String[] args) {
    LibraryFacade library = new LibraryFacade();

    library.addBook(new ScienceBookCreator(), "Physics Basics", "Hawking");
    library.addBook(new LiteratureBookCreator(), "Hamlet", "Shakespeare");

    library.setSearchStrategy(new TitleSearchStrategy());
    library.searchBook("Hamlet");

    library.setSearchStrategy(new AuthorSearchStrategy());
    library.searchBook("Hawking");

    library.searchBook("Albert");
  }
}
