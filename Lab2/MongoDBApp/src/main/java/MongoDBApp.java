import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.Book;
import org.example.BookBorrowing;
import org.example.LibraryMember;

import java.util.Random;
import java.util.Scanner;

public class MongoDBApp {

    public static void saveOperation(MongoDatabase database) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zapisać w bazie danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            final Random rnd = new Random(System.currentTimeMillis());
            long id = Math.abs(rnd.nextInt());
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    Book book = new Book();
                    book.setId(Long.toString(id));
                    System.out.println("Zapis książki do bazy danych.");
                    System.out.println("Podaj tytuł ksiązki: ");
                    book.setTitle(scanner.next());
                    System.out.println("Podaj autora książki: ");
                    book.setAuthor(scanner.next());
                    System.out.println("Podaj cenę książki: ");
                    book.setPrice(scanner.nextFloat());

                    MongoCollection<Book> books = database.getCollection("book", Book.class);
                    books.insertOne(book);
                    System.out.println("Zapisano książkę: " + book + "\n");
                    break;
                case 2:
                    LibraryMember libraryMember = new LibraryMember();
                    libraryMember.setId(Long.toString(id));
                    System.out.println("Zapis członka biblioteki do bazy danych.");
                    System.out.println("Podaj imie członka biblioteki: ");
                    libraryMember.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko członka biblioteki: ");
                    libraryMember.setLastName(scanner.next());
                    System.out.println("Podaj adres członka biblioteki: ");
                    libraryMember.setAddress(scanner.next());

                    MongoCollection<LibraryMember> libraryMembers = database.getCollection("libraryMember", LibraryMember.class);
                    libraryMembers.insertOne(libraryMember);
                    System.out.println("Zapisano członka biblioteki: " + libraryMember + "\n");
                    break;
                case 3:
                    BookBorrowing bookBorrowing = new BookBorrowing();
                    bookBorrowing.setId(Long.toString(id));
                    System.out.println("Zapis wypożyczenia do bazy danych.");
                    System.out.println("Podaj id członka biblioteki biblioteki: ");
                    bookBorrowing.setMemberId(scanner.next());
                    System.out.println("Podaj ISBN książki biblioteki biblioteki: ");
                    bookBorrowing.setISBN(scanner.next());
                    System.out.println("Podaj ilość dni trwania wypożyczenia biblioteki: ");
                    bookBorrowing.setNumberOfDays(scanner.nextInt());

                    MongoCollection<BookBorrowing> bookBorrowings = database.getCollection("bookBorrowing", BookBorrowing.class);
                    bookBorrowings.insertOne(bookBorrowing);
                    System.out.println("Zapisano wypożyczenie: " + bookBorrowing + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void updateOperation(MongoDatabase database) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zaktualizować w bazie danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji akutalizacji do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    MongoCollection<Book> books = database.getCollection("book", Book.class);
                    Book book = new Book();
                    System.out.println("Podaj id książki do zaktualizowania: ");
                    book.setId(scanner.next());
                    System.out.println("Aktualizacja książki w bazie danych.");
                    System.out.println("Podaj tytuł ksiązki: ");
                    book.setTitle(scanner.next());
                    System.out.println("Podaj autora książki: ");
                    book.setAuthor(scanner.next());
                    System.out.println("Podaj cenę książki: ");
                    book.setPrice(scanner.nextFloat());

                    Document filterByBookId = new Document("_id", book.getId());
                    books.findOneAndReplace(filterByBookId, book);
                    System.out.println("Zaktualizowano książkę: " + book + "\n");
                    break;
                case 2:
                    MongoCollection<LibraryMember> libraryMembers = database.getCollection("libraryMember", LibraryMember.class);
                    LibraryMember libraryMember = new LibraryMember();
                    System.out.println("Podaj id członka biblioteki do zaktualizowania: ");
                    libraryMember.setId(scanner.next());
                    System.out.println("Aktualizacja członka biblioteki w bazie danych.");
                    System.out.println("Podaj imie członka biblioteki: ");
                    libraryMember.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko członka biblioteki: ");
                    libraryMember.setLastName(scanner.next());
                    System.out.println("Podaj adres członka biblioteki: ");
                    libraryMember.setAddress(scanner.next());

                    Document filterByMemberId = new Document("_id", libraryMember.getId());
                    libraryMembers.findOneAndReplace(filterByMemberId, libraryMember);
                    System.out.println("Zaktualizowano członka biblioteki: " + libraryMember + "\n");
                    break;
                case 3:
                    MongoCollection<BookBorrowing> bookBorrowings = database.getCollection("bookBorrowing", BookBorrowing.class);
                    BookBorrowing bookBorrowing = new BookBorrowing();
                    System.out.println("Podaj id wypożyczenia do zaktualizowania: ");
                    bookBorrowing.setId(scanner.next());
                    System.out.println("Aktualizacja wypożyczenia w bazie danych.");
                    System.out.println("Podaj id członka biblioteki biblioteki: ");
                    bookBorrowing.setMemberId(scanner.next());
                    System.out.println("Podaj ISBN książki biblioteki biblioteki: ");
                    bookBorrowing.setISBN(scanner.next());
                    System.out.println("Podaj ilość dni trwania wypożyczenia biblioteki: ");
                    bookBorrowing.setNumberOfDays(scanner.nextInt());

                    Document filterByBorrowId = new Document("_id", bookBorrowing.getId());
                    bookBorrowings.findOneAndReplace(filterByBorrowId, bookBorrowing);
                    System.out.println("Zaktualizowano wypożyczenie: " + bookBorrowing + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void deleteOperation(MongoDatabase database) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś usunąć z bazy danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji usuwania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    MongoCollection<Book> books = database.getCollection("book", Book.class);
                    System.out.println("Podaj id książki do usunięcia: ");
                    String bookID = scanner.next();
                    books.deleteOne(Filters.eq("_id", bookID));
                    System.out.println("Usunięto książkę o id: " + bookID + "\n");
                    break;
                case 2:
                    MongoCollection<LibraryMember> libraryMembers = database.getCollection("libraryMember", LibraryMember.class);
                    System.out.println("Podaj id członka biblioteki do usunięcia: ");
                    String libraryMemberId = scanner.next();
                    libraryMembers.deleteOne(Filters.eq("_id", libraryMemberId));
                    System.out.println("Usunięto członka biblioteki o id: " + libraryMemberId + "\n");
                    break;
                case 3:
                    MongoCollection<BookBorrowing> bookBorrowings = database.getCollection("bookBorrowing", BookBorrowing.class);
                    System.out.println("Podaj id wypożyczenia do usunięcia: ");
                    String bookBorrowingId = scanner.next();
                    bookBorrowings.deleteOne(Filters.eq("_id", bookBorrowingId));
                    System.out.println("Usunięto wypożyczenie o id: " + bookBorrowingId + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void getOperation(MongoDatabase database) {
        MongoCollection<Book> books = database.getCollection("book", Book.class);
        MongoCollection<LibraryMember> libraryMembers = database.getCollection("libraryMember", LibraryMember.class);
        MongoCollection<BookBorrowing> bookBorrowings = database.getCollection("bookBorrowing", BookBorrowing.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś pobrać z bazy danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji pobierania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Pobieranie książki z bazy danych po: \n1 - ISBN \n2 - Tytule");
                    int bookSelectedOption = scanner.nextInt();
                    switch (bookSelectedOption) {
                        case 1:
                            System.out.println("Podaj ISBN książki: ");
                            String ISBN = scanner.next();
                            Book book = books.find(Filters.eq("_id", ISBN)).first();
                            System.out.println("Znaleziono książke: " + book);
                            break;
                        case 2:
                            System.out.println("Podaj tytuł książki: ");
                            String title = scanner.next();
                            Book book1 = books.find(Filters.eq("title", title)).first();
                            System.out.println("Znaleziono książke: " + book1);
                            break;
                        default:
                            return;
                    }
                    break;
                case 2:
                    System.out.println("Pobieranie członka biblioteki z bazy danych po: \n1 - Id \n2 - Imieniu");
                    int memberSelectedOption = scanner.nextInt();
                    switch (memberSelectedOption) {
                        case 1:
                            System.out.println("Podaj id członka biblioteki: ");
                            String memberId = scanner.next();
                            LibraryMember lm = libraryMembers.find(Filters.eq("_id", memberId)).first();
                            System.out.println("Znaleziono książke: " + lm);
                            break;
                        case 2:
                            System.out.println("Podaj imię członka biblioteki: ");
                            String firstName = scanner.next();
                            LibraryMember lm1 = libraryMembers.find(Filters.eq("firstName", firstName)).first();
                            System.out.println("Znaleziono członka biblioteki: " + lm1);
                            break;
                        default:
                            return;
                    }
                    break;
                case 3:
                    System.out.println("Pobieranie wypożyczenia z bazy danych po: \n1 - Id \n2 - Ilości dni");
                    int borrowingSelectedOption = scanner.nextInt();
                    switch (borrowingSelectedOption) {
                        case 1:
                            System.out.println("Podaj id wypożyczenia: ");
                            String borrowingId = scanner.next();
                            BookBorrowing bb = bookBorrowings.find(Filters.eq("_id", borrowingId)).first();
                            System.out.println("Znaleziono wypożyczenie: " + bb);
                            break;
                        case 2:
                            System.out.println("Podaj ilość dni wypożyczenia: ");
                            int numberOfDays = scanner.nextInt();
                            BookBorrowing bb1 = bookBorrowings.find(Filters.eq("numberOfDays", numberOfDays)).first();
                            System.out.println("Znaleziono wypożyczenie: " + bb1);
                            break;
                        default:
                            return;
                    }
                    break;
                default: return;
            }
        }
    }

    public static void dataProcessing(MongoDatabase database) {
        System.out.println("Obliczanie sumy cen wszystkich książek");
        MongoCollection<Book> books = database.getCollection("book", Book.class);
        float sum = 0;
        for (var book: books.find()) {
            sum += book.getPrice();
        }
        System.out.println("Suma cen wszystkich książek: " + sum);
    }

    public static void main(String[] args) {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry).build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("local");
            Scanner scanner = new Scanner(System.in);
            Thread.sleep(100);

            System.out.println("Połączono z bazą MongoDB !");
            while (true) {
                System.out.println("Witamy w systemie biblioteki!");
                System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                        + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
                System.out.println("Proszę wybrać pożądaną operacje: ");
                int selectedOption = scanner.nextInt();
                switch (selectedOption) {
                    case 1:
                        saveOperation(database);
                        break;
                    case 2:
                        updateOperation(database);
                        break;
                    case 3:
                        deleteOperation(database);
                        break;
                    case 4:
                        getOperation(database);
                        break;
                    case 5:
                        dataProcessing(database);
                        break;
                    default:
                        mongoClient.close();
                        return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
