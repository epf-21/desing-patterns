# Modulo de Biblioteca

Un proyecto que implementa un modulo de biblioteca completo, demostrando la aplicación práctica de:

- **Arquitectura en 3 capas** con separación clara de responsabilidades
- **Principios SOLID** aplicados
- **Patrones de diseño** (Factory Method, Facade, Strategy)

## Estructura del Proyecto

```
design-patterns/
├── presentation/           # Capa de Presentación
│   └── Main.java
├── business/              # Capa de Lógica de Negocio
│   ├── facade/            # Patrón Facade
│   │   └── LibraryFacade.java
│   ├── factoryMethod/     #  Patrón Factory Method
│   │   ├── Book.java
│   │   ├── BookCreator.java
│   │   ├── ScienceBook.java
│   │   ├── LiteratureBook.java
│   │   ├── ScienceBookCreator.java
│   │   └── LiteratureBookCreator.java
│   └── strategy/          # Patrón Strategy
│       ├── SearchStrategy.java
│       ├── TitleSearchStrategy.java
│       └── AuthorSearchStrategy.java
└── data/                  # Capa de Acceso a Datos
    └── BookRepository.java
```

## Arquitectura de 3 Capas

### Capa de Presentación (presentation/)

- **Responsabilidad**: Interfaz de usuario (cli) y punto de entrada
- **Características**:
  - Contiene únicamente la clase `Main.java`
  - Solo interactúa con la fachada (`LibraryFacade`)
  - No tiene conocimiento de la lógica de negocio interna
  - Mantiene la separación entre UI y lógica de negocio

### Capa de Lógica de Negocio (business/)

- **Responsabilidad**: Reglas de negocio y coordinación de operaciones
- **Componentes**:
  - **Factory Method**: Creación flexible de diferentes tipos de libros
  - **Strategy**: Algoritmos de búsqueda intercambiables
  - **Facade**: Interfaz simplificada para todas las operaciones
- **Características**:
  - Encapsula toda la lógica de negocio
  - Aplica patrones de diseño para flexibilidad y mantenibilidad
  - Independiente de la capa de presentación y datos

### Capa de Acceso a Datos (data/)

- **Responsabilidad**: Persistencia y recuperación de datos
- **Características**:
  - Repositorio simple en memoria (`BookRepository`)
  - Encapsula el manejo de almacenamiento
  - Fácilmente intercambiable por implementaciones con base de datos

## Patrones de Diseño Implementados

### Factory Method (Patrón Creacional)

**Problema que resuelve**: Crear objetos sin especificar las clases exactas de los objetos a crear.

**Implementación**:

```java
public abstract class BookCreator {
    public abstract Book createBook(String title, String author);
}

public class ScienceBookCreator extends BookCreator {
    public Book createBook(String title, String author) {
        return new ScienceBook(title, author);
    }
}
```

**Ventajas**:

- Desacopla la creación de objetos del código cliente
- Fácil extensión para nuevos tipos de libros
- Cumple con el Principio Abierto/Cerrado

### Facade (Patrón Estructural)

**Problema que resuelve**: Proporcionar una interfaz simplificada a un subsistema complejo.

**Implementación**:

```java
public class LibraryFacade {
    private BookRepository repository;
    private SearchStrategy strategy;

    public void addBook(BookCreator creator, String title, String author) {
        Book book = creator.createBook(title, author);
        repository.save(book);
    }

    public void searchBook(String keyword) {
        Book found = strategy.search(repository.findAll(), keyword);
        // Lógica de presentación de resultados
    }
}
```

**Ventajas**:

- Simplifica las operaciones complejas del sistema
- Oculta la complejidad interna al cliente

### 🎯 Strategy (Patrón de Comportamiento)

**Problema que resuelve**: Seleccionar algoritmos dinámicamente.

**Implementación**:

```java
public interface SearchStrategy {
    Book search(List<Book> books, String keyword);
}

public class TitleSearchStrategy implements SearchStrategy {
    public Book search(List<Book> books, String keyword) {
        // Búsqueda por título
    }
}

public class AuthorSearchStrategy implements SearchStrategy {
    public Book search(List<Book> books, String keyword) {
        // Búsqueda por autor
    }
}
```

**Ventajas**:

- Intercambio dinámico de algoritmos
- Fácil adición de nuevas estrategias de búsqueda

## Principios SOLID Aplicados

| Principio                           | Aplicación en el Proyecto                                                                     |
| ----------------------------------- | --------------------------------------------------------------------------------------------- |
| **SRP** - Responsabilidad Única     | Cada clase tiene una única razón para cambiar (ej: `BookRepository` solo maneja persistencia) |
| **OCP** - Abierto/Cerrado           | Extensible a nuevos tipos de libros sin modificar código existente                            |
| **LSP** - Sustitución de Liskov     | Las implementaciones concretas pueden reemplazar a sus abstracciones                          |
| **ISP** - Segregación de Interfaces | Interfaces específicas como `SearchStrategy`                                                  |
| **DIP** - Inversión de Dependencias | Las clases dependen de abstracciones, no de implementaciones concretas                        |

## Ejecución del Proyecto

### Compilación

```bash
javac **/*.java presentation/Main.java -d out

```

### Ejecución

```bash
java -cp out presentation.Main
```

### Resultado Esperado

```
Libro registrado: Título: Física Cuántica, Autor: Hawking
Libro registrado: Título: Don Quijote, Autor: Cervantes
Libro encontrado: Título: Don Quijote, Autor: Cervantes
Libro encontrado: Título: Física Cuántica, Autor: Hawking
No se encontro ningun libro con la palabra clave: Albert
```
