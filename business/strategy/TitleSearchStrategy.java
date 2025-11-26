package business.strategy;

import business.factoryMethod.Book;
import java.util.List;

public class TitleSearchStrategy implements SearchStrategy {
  @Override
  public Book search(List<Book> books, String keyword) {
    for (Book book : books) {
      if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
        return book;
      }
    }
    return null;
  }
}
