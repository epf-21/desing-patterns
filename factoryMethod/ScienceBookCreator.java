package factoryMethod;

public class ScienceBookCreator extends BookCreator {
  @Override
  public Book createBook(String title, String author) {
    return new ScienceBook(title, author);
  }
}
