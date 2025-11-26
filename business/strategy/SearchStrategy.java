package business.strategy;

import business.factoryMethod.Book;
import java.util.List;

public interface SearchStrategy {
  Book search(List<Book> books, String keyword);
}
