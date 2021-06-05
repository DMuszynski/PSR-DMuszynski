import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.*;

public class AmazonDynamoDBApp {

    public static void saveOperation(AmazonDynamoDB database) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zapisać w bazie danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            final DynamoDBMapper mapper = new DynamoDBMapper(database);
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

                    mapper.save(book);
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

                    mapper.save(libraryMember);
                    System.out.println("Zapisano członka biblioteki: " + libraryMember + "\n");
                    break;
                case 3:
                    BookBorrowing bookBorrowing = new BookBorrowing();
                    bookBorrowing.setId(Long.toString(id));
                    System.out.println("Zapis wypożyczenia do bazy danych.");
                    System.out.println("Podaj id członka biblioteki: ");
                    bookBorrowing.setMemberId(scanner.next());
                    System.out.println("Podaj ISBN książki biblioteki: ");
                    bookBorrowing.setISBN(scanner.next());
                    System.out.println("Podaj ilość dni trwania wypożyczenia biblioteki: ");
                    bookBorrowing.setNumberOfDays(scanner.nextInt());

                    mapper.save(bookBorrowing);
                    System.out.println("Zapisano wypożyczenie: " + bookBorrowing + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void updateOperation(AmazonDynamoDB database) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zaktualizować w bazie danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji akutalizacji do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            final DynamoDBMapper mapper = new DynamoDBMapper(database);
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
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

                    mapper.save(book);
                    System.out.println("Zaktualizowano książkę: " + book + "\n");
                    break;
                case 2:
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

                    mapper.save(libraryMember);
                    System.out.println("Zaktualizowano członka biblioteki: " + libraryMember + "\n");
                    break;
                case 3:
                    BookBorrowing bookBorrowing = new BookBorrowing();
                    System.out.println("Podaj id wypożyczenia do zaktualizowania: ");
                    bookBorrowing.setId(scanner.next());
                    System.out.println("Aktualizacja wypożyczenia w bazie danych.");
                    System.out.println("Podaj id członka biblioteki: ");
                    bookBorrowing.setMemberId(scanner.next());
                    System.out.println("Podaj ISBN książki biblioteki: ");
                    bookBorrowing.setISBN(scanner.next());
                    System.out.println("Podaj ilość dni trwania wypożyczenia biblioteki: ");
                    bookBorrowing.setNumberOfDays(scanner.nextInt());

                    mapper.save(bookBorrowing);
                    System.out.println("Zaktualizowano wypożyczenie: " + bookBorrowing + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void deleteOperation(AmazonDynamoDB database) {
        final Scanner scanner = new Scanner(System.in);
        final DynamoDBMapper mapper = new DynamoDBMapper(database);
        while (true) {
            System.out.println("Co chciałbyś usunąć z bazy danych ?");
            System.out.print("1 - Książkę \n2 - Członka biblioteki \n3 - Wypożyczenie \n" +
                    "default - zakończenie operacji usuwania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj id książki do usunięcia: ");
                    Book book = new Book();
                    book.setId(scanner.next());
                    mapper.delete(book);
                    System.out.println("Usunięto książkę o id: " + book.getId() + "\n");
                    break;
                case 2:
                    System.out.println("Podaj id członka biblioteki do usunięcia: ");
                    LibraryMember libraryMember = new LibraryMember();
                    libraryMember.setId(scanner.next());
                    mapper.delete(libraryMember);
                    System.out.println("Usunięto członka biblioteki o id: " + libraryMember.getId() + "\n");
                    break;
                case 3:
                    System.out.println("Podaj id wypożyczenia do usunięcia: ");
                    BookBorrowing bookBorrowing = new BookBorrowing();
                    bookBorrowing.setId(scanner.next());
                    mapper.delete(bookBorrowing);
                    System.out.println("Usunięto wypożyczenie o id: " + bookBorrowing.getId() + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void getOperation(AmazonDynamoDB database) {
        final DynamoDBMapper mapper = new DynamoDBMapper(database);
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
                            Book book = mapper.load(Book.class, ISBN);
                            System.out.println("Znaleziono książke: " + book);
                            break;
                        case 2:
                            System.out.println("Podaj tytuł książki: ");
                            String title = scanner.next();

                            Map<String, AttributeValue> eav = new HashMap<>();
                            eav.put(":title", new AttributeValue().withS(title));
                            DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                                .withFilterExpression("begins_with(title, :title)")
                                .withExpressionAttributeValues(eav);
                            Book book1 = mapper.scan(Book.class, queryExpression).get(0);
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
                            LibraryMember lm = mapper.load(LibraryMember.class, memberId);
                            System.out.println("Znaleziono książke: " + lm);
                            break;
                        case 2:
                            System.out.println("Podaj imię członka biblioteki: ");
                            String firstName = scanner.next();

                            Map<String, AttributeValue> eav = new HashMap<>();
                            eav.put(":firstName", new AttributeValue().withS(firstName));
                            DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                                .withFilterExpression("begins_with(firstName, :firstName)")
                                .withExpressionAttributeValues(eav);
                            LibraryMember lm1 = mapper.scan(LibraryMember.class, queryExpression).get(0);
                            System.out.println("Znaleziono członka biblioteki: " + lm1);
                            break;
                        default:
                            return;
                    }
                    break;
                case 3:
                    System.out.println("Pobieranie wypożyczenia z bazy danych po: \n1 - Id \n2 - ISBN");
                    int borrowingSelectedOption = scanner.nextInt();
                    switch (borrowingSelectedOption) {
                        case 1:
                            System.out.println("Podaj id wypożyczenia: ");
                            String borrowingId = scanner.next();
                            BookBorrowing bb = mapper.load(BookBorrowing.class, borrowingId);
                            System.out.println("Znaleziono wypożyczenie: " + bb);
                            break;
                        case 2:
                            System.out.println("Podaj ISBN książki: ");
                            String ISBN = scanner.next();

                            Map<String, AttributeValue> eav = new HashMap<>();
                            eav.put(":ISBN", new AttributeValue().withS(ISBN));
                            DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                                .withFilterExpression("ISBN = :ISBN")
                                .withExpressionAttributeValues(eav);
                            BookBorrowing bb1 = mapper.scan(BookBorrowing.class, queryExpression).get(0);

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

    public static void dataProcessing(AmazonDynamoDB database) {
        System.out.println("Obliczanie sumy cen wszystkich książek");
        final DynamoDBMapper mapper = new DynamoDBMapper(database);
        List<Book> bookBorrowings = mapper.scan(Book.class, new DynamoDBScanExpression());
        float sum = 0;
        for (Book book: bookBorrowings) {
            sum += book.getPrice();
        }
        System.out.println("Suma cen wszystkich książek: " + sum);
    }

    public static void main(String[] args) {
        BasicAWSCredentials credentials = new BasicAWSCredentials("access_key_id", "secret_key_id");
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Połączono z bazą Amazon DynamoDB !");
        while (true) {
            System.out.println("Witamy w systemie biblioteki!");
            System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                    + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
            System.out.println("Proszę wybrać pożądaną operacje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    saveOperation(client);
                    break;
                case 2:
                    updateOperation(client);
                    break;
                case 3:
                    deleteOperation(client);
                    break;
                case 4:
                    getOperation(client);
                    break;
                case 5:
                    dataProcessing(client);
                    break;
                default:
                    client.shutdown();
                    return;
            }
        }
    }
}