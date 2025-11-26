package business.strategy;

import business.factoryMethod.Book;
import java.util.List;

public class AuthorSearchStrategy implements SearchStrategy {
  @Override
  public Book search(List<Book> books, String keyword) {
    for (Book book : books) {
      if (book.getAuthor().toLowerCase().equals(keyword.toLowerCase())) {
        return book;
      }
    }
    return null;
  }
}
