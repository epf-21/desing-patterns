package facade;

import factoryMethod.*;
import strategy.SearchStrategy;

import java.util.List;
import java.util.ArrayList;

public class LibraryFacade {
  private List<Book> books = new ArrayList<>();
  private SearchStrategy strategy;

  public void setSearchStrategy(SearchStrategy strategy) {
    this.strategy = strategy;
  }

  public void addBook(BookCreator creator, String title, String author) {
    Book book = creator.createBook(title, author);
    books.add(book);
    System.out.println("Libro agregado: " + book.getInfo());
  }

  public void searchBook(String keyword) {
    if (this.strategy == null) {
      System.out.println("La estrategia de busqueda no esta seleccionada.");
      return;
    }

    Book result = this.strategy.search(books, keyword);
    if (result != null) {
      System.out.println("Libro encontrado: " + result.getInfo());
    } else {
      System.out.println("No se encontro ningun libro con la palabra clave: " + keyword);
    }
  }

}
