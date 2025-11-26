package business.facade;

import business.factoryMethod.*;
import business.strategy.SearchStrategy;
import data.BookRepository;

public class LibraryFacade {
  private BookRepository repository;
  private SearchStrategy strategy;

  public LibraryFacade(BookRepository repository) {
    this.repository = repository;
  }

  public void setSearchStrategy(SearchStrategy strategy) {
    this.strategy = strategy;
  }

  public void addBook(BookCreator creator, String title, String author) {
    Book book = creator.createBook(title, author);
    repository.save(book);
    System.out.println("Libro registrado: " + book.getInfo());
  }

  public void searchBook(String keyword) {
    if(strategy == null){
      System.out.println("Seleccione una estrategia de búsqueda.");
      return;
    }

    Book found = strategy.search(repository.findAll(), keyword);

    if(found != null)
      System.out.println("Libro encontrado: " + found.getInfo());
    else
      System.out.println("No se encontro ningun libro con la palabra clave: " + keyword);
  }

}
