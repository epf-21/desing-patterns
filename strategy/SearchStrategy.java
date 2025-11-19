package strategy;

import factoryMethod.Book;
import java.util.List;

public interface SearchStrategy {
  Book search(List<Book> books, String keyword);
}
