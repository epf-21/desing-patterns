package factoryMethod;

public class LiteratureBookCreator extends BookCreator {
  @Override
  public Book createBook(String title, String author) {
    return new LiteratureBook(title, author);
  }
}
