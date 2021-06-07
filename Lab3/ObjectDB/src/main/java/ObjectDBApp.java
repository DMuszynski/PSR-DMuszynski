import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ObjectDBApp {
    public static final Scanner scanner = new Scanner(System.in);

    public static void saveOperation(EntityManager em) {
        while (true) {
            System.out.println("Co chciałbyś zapisać w bazie danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podróży \n3 - Podróż \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            final Random rnd = new Random(System.currentTimeMillis());
            long id = Math.abs(rnd.nextInt());
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    Client client = new Client();
                    client.setId(id);
                    System.out.println("Zapis klienta do bazy danych.");
                    System.out.println("Podaj imię klienta: ");
                    client.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko klienta: ");
                    client.setLastName(scanner.next());
                    System.out.println("Podaj adres klienta: ");
                    client.setAddress(scanner.next());

                    em.getTransaction().begin();
                    em.persist(client);
                    em.getTransaction().commit();

                    System.out.println("Zapisano klienta: " + client + "\n");
                    break;
                case 2:
                    Place place = new Place();
                    place.setId(id);
                    System.out.println("Zapis miejsca podróży do bazy danych.");
                    System.out.println("Podaj kraj podróży: ");
                    place.setCountry(scanner.next());
                    System.out.println("Podaj miasto podróży: ");
                    place.setCity(scanner.next());
                    System.out.println("Podaj adres podróży: ");
                    place.setAddress(scanner.next());

                    em.getTransaction().begin();
                    em.persist(place);
                    em.getTransaction().commit();

                    System.out.println("Zapisano miejsce podróży: " + place + "\n");
                    break;
                case 3:
                    Travel travel = new Travel();
                    travel.setId(id);
                    System.out.println("Zapis podróży do bazy danych.");
                    System.out.println("Podaj id klienta biura podróży: ");
                    travel.setClientId(scanner.nextLong());
                    System.out.println("Podaj id miejsca podróży: ");
                    travel.setPlaceId(scanner.nextLong());
                    System.out.println("Podaj ilość dni trwania podróży: ");
                    travel.setNumberOfDays(scanner.nextInt());
                    System.out.println("Podaj koszt podróży: ");
                    travel.setPrice(scanner.nextFloat());

                    em.getTransaction().begin();
                    em.persist(travel);
                    em.getTransaction().commit();

                    System.out.println("Zapisano podróż: " + travel + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void updateOperation(EntityManager em) {
        while (true) {
            System.out.println("Co chciałbyś zaktualizować w bazie danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podróży \n3 - Podróż \n" +
                    "default - zakończenie operacji aktualizacji w bazie danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    Client client = new Client();
                    System.out.println("Podaj id klienta do zaktualizowania: ");
                    client.setId(scanner.nextLong());
                    System.out.println("Aktualizacja klienta w bazie danych.");
                    System.out.println("Podaj imię klienta: ");
                    client.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko klienta: ");
                    client.setLastName(scanner.next());
                    System.out.println("Podaj adres klienta: ");
                    client.setAddress(scanner.next());

                    em.getTransaction().begin();
                    em.merge(client);
                    em.getTransaction().commit();

                    System.out.println("Zaktualizowano klienta: " + client + "\n");
                    break;
                case 2:
                    Place place = new Place();
                    System.out.println("Podaj id miejsca podróży do zaktualizowania: ");
                    place.setId(scanner.nextLong());
                    System.out.println("Aktualizacja miejsca podróży w bazie danych.");
                    System.out.println("Podaj kraj podróży: ");
                    place.setCountry(scanner.next());
                    System.out.println("Podaj miasto podróży: ");
                    place.setCity(scanner.next());
                    System.out.println("Podaj adres podróży: ");
                    place.setAddress(scanner.next());

                    em.getTransaction().begin();
                    em.merge(place);
                    em.getTransaction().commit();

                    System.out.println("Zaktualizowano miejsce podróży: " + place + "\n");
                    break;
                case 3:
                    Travel travel = new Travel();
                    System.out.println("Podaj id podróży do zaktualizowania: ");
                    travel.setId(scanner.nextLong());
                    System.out.println("Aktualizacja podróży w bazie danych.");
                    System.out.println("Podaj id klienta biura podróży: ");
                    travel.setClientId(scanner.nextLong());
                    System.out.println("Podaj id miejsca podróży: ");
                    travel.setPlaceId(scanner.nextLong());
                    System.out.println("Podaj ilość dni trwania podróży: ");
                    travel.setNumberOfDays(scanner.nextInt());
                    System.out.println("Podaj koszt podróży: ");
                    travel.setPrice(scanner.nextFloat());

                    em.getTransaction().begin();
                    em.merge(travel);
                    em.getTransaction().commit();

                    System.out.println("Zaktualizowano podróż: " + travel + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void deleteOperation(EntityManager em) {
        while (true) {
            System.out.println("Co chciałbyś usunąć z bazy danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podróży \n3 - Podróż \n" +
                    "default - zakończenie operacji usuwania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj id klienta do usunięcia: ");
                    long clientId = scanner.nextLong();

                    Client client = em.find(Client.class, clientId);
                    em.getTransaction().begin();
                    em.remove(client);
                    em.getTransaction().commit();

                    System.out.println("Usunięto klienta: " + client + "\n");
                    break;
                case 2:
                    System.out.println("Podaj id miejsca podróży do usunięcia: ");
                    long placeId = scanner.nextLong();

                    Place place = em.find(Place.class, placeId);
                    em.getTransaction().begin();
                    em.remove(place);
                    em.getTransaction().commit();

                    System.out.println("Usunięto miejsce podróży: " + place + "\n");
                    break;
                case 3:
                    System.out.println("Podaj id podróży do usunięcia: ");
                    long travelId = scanner.nextLong();

                    Travel travel = em.find(Travel.class, travelId);
                    em.getTransaction().begin();
                    em.remove(travel);
                    em.getTransaction().commit();

                    System.out.println("Usunięto podróż: " + travel + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void getOperation(EntityManager em) {
        TypedQuery<Client> query = em.createQuery("SELECT t FROM Client t", Client.class);
        var results= query.getResultList();
        for (var p : results) {
            System.out.println(p);
        }

        while (true) {
            System.out.println("Co chciałbyś pobrać z bazy danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podróży \n3 - Podróż \n" +
                    "default - zakończenie operacji pobierania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Pobieranie klienta z bazy danych po: \n1 - Id \n2 - Imieniu");
                    int clientSelectedOption = scanner.nextInt();
                    switch (clientSelectedOption) {
                        case 1:
                            System.out.println("Podaj id klienta: ");
                            Client client = em.getReference(Client.class, scanner.nextLong());
                            System.out.println("Znaleziono klienta: " + client);
                            break;
                        case 2:
                            System.out.println("Podaj imię klienta: ");
                            Query selectClientByFirstNameQuery = em
                                .createQuery("SELECT c FROM Client c WHERE c.firstName =:firstName");
                            selectClientByFirstNameQuery.setParameter("firstName", scanner.next());

                            System.out.println("Znaleziono klienta: " + selectClientByFirstNameQuery.getSingleResult());
                            break;
                        default:
                            return;
                    }
                    break;
                case 2:
                    System.out.println("Pobieranie miejsca podróży z bazy danych po: \n1 - Id \n2 - Kraju");
                    int placeSelectedOption = scanner.nextInt();
                    switch (placeSelectedOption) {
                        case 1:
                            System.out.println("Podaj id miejsca podróży: ");
                            Place place = em.getReference(Place.class, scanner.nextLong());
                            System.out.println("Znaleziono miejsce podróży: " + place);
                            break;
                        case 2:
                            System.out.println("Podaj kraj miejsca podróży: ");
                            Query selectPlaceByCountryQuery = em
                                .createQuery("SELECT p FROM Place p WHERE p.country in :country");
                            selectPlaceByCountryQuery.setParameter("country", scanner.next());
                            System.out.println("Znaleziono miejsce podróży: " + selectPlaceByCountryQuery.getSingleResult());
                            break;
                        default:
                            return;
                    }
                    break;
                case 3:
                    System.out.println("Pobieranie podróży z bazy danych po: \n1 - Id \n2 - Cenie");
                    int travelSelectedOption = scanner.nextInt();
                    switch (travelSelectedOption) {
                        case 1:
                            System.out.println("Podaj id podróży: ");
                            Travel travel = em.getReference(Travel.class, scanner.nextLong());
                            System.out.println("Znaleziono podróż: " + travel);
                            break;
                        case 2:
                            System.out.println("Podaj cenę podróży: ");
                            Query selectTravelByPriceQuery = em
                                .createQuery("SELECT t FROM Travel t WHERE t.price = :price");
                            selectTravelByPriceQuery.setParameter("price", scanner.nextFloat());
                            System.out.println("Znaleziono podróż: " + selectTravelByPriceQuery.getSingleResult());
                            break;
                        default:
                            return;
                    }
                    break;
                default: return;
            }
        }
    }

    public static void dataProcessing(EntityManager em) {
        System.out.println("Obliczanie sumy cen wszystkich podróży");
        Query q1 = em.createQuery("SELECT SUM(t.price) FROM Travel t");
        System.out.println("Suma cen wszystkich podróży: " + q1.getSingleResult());
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("$objectdb/db/p2.odb");
        EntityManager em = emf.createEntityManager();

        System.out.println("Połączono z bazą ObjectDB !");
        while (true) {
            System.out.println("Witamy w systemie biura podróży!");
            System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                    + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
            System.out.println("Proszę wybrać pożądaną operacje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    saveOperation(em);
                    break;
                case 2:
                    updateOperation(em);
                    break;
                case 3:
                    deleteOperation(em);
                    break;
                case 4:
                    getOperation(em);
                    break;
                case 5:
                    dataProcessing(em);
                    break;
                default:
                    em.close();
                    emf.close();
                    return;
            }
        }
    }
}