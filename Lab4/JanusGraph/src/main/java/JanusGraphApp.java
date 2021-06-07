import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory;

import java.util.*;

public class JanusGraphApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void saveOperation(GraphTraversalSource g) {
        while (true) {
            System.out.println("Co chciałbyś zapisać w bazie danych ?");
            System.out.print("1 - Policjanta \n2 - Poszkodowanego \n3 - Przestępstwo \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            final Random rnd = new Random(System.currentTimeMillis());
            String id = String.valueOf(Math.abs(rnd.nextInt()));
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    Offender offender = new Offender();
                    offender.setId(id);
                    System.out.println("Zapis policjanta do bazy danych.");
                    System.out.println("Podaj imię policjanta: ");
                    offender.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko policjanta: ");
                    offender.setLastName(scanner.next());
                    System.out.println("Podaj pensje policjanta: ");
                    offender.setSalary(scanner.nextFloat());

                    g.addV("Offender")
                        .property("id", offender.getId())
                        .property("firstName", offender.getFirstName())
                        .property("lastName", offender.getLastName())
                        .property("salary", offender.getSalary())
                        .next();

                    System.out.println("Zapisano policjanta: " + offender + "\n");
                    break;
                case 2:
                    Victim victim = new Victim();
                    victim.setId(id);
                    System.out.println("Zapis poszkodowanego do bazy danych.");
                    System.out.println("Podaj imię poszkodowanego: ");
                    victim.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko poszkodowanego: ");
                    victim.setLastName(scanner.next());
                    System.out.println("Podaj adres poszkodowanego: ");
                    victim.setAddress(scanner.next());

                    g.addV("Victim")
                        .property("id", victim.getId())
                        .property("firstName", victim.getFirstName())
                        .property("lastName", victim.getLastName())
                        .property("address", victim.getAddress())
                        .next();

                    System.out.println("Zapisano poszkodowanego: " + victim + "\n");
                    break;
                case 3:
                    Crime crime = new Crime();
                    crime.setId(id);
                    System.out.println("Zapis przestępstwa do bazy danych.");
                    System.out.println("Podaj id policjanta: ");
                    crime.setIdOffender(scanner.next());
                    System.out.println("Podaj id poszkodowanego: ");
                    crime.setIdVictim(scanner.next());
                    System.out.println("Podaj typ przestępstwa: ");
                    crime.setCrimeType(scanner.next());
                    System.out.println("Podaj datę przestępstwa: ");
                    crime.setCrimeDate(scanner.next());

                    g.addV("Crime")
                        .property("id", crime.getId())
                        .property("idOffender", crime.getIdOffender())
                        .property("idVictim", crime.getIdVictim())
                        .property("crimeType", crime.getCrimeType())
                        .property("crimeDate", crime.getCrimeDate())
                        .next();

                    System.out.println("Zapisano przestępstwo: " + crime + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void updateOperation(GraphTraversalSource g) {
        while (true) {
            System.out.println("Co chciałbyś zaktualizować w bazie danych ?");
            System.out.print("1 - Policjanta \n2 - Poszkodowanego \n3 - Przestępstwo \n" +
                    "default - zakończenie operacji aktualizacji w bazie danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    Offender offender = new Offender();
                    System.out.println("Podaj id policjanta do zaktualizowania: ");
                    offender.setId(scanner.next());
                    System.out.println("Aktualizacja policjanta w bazie danych.");
                    System.out.println("Podaj imię policjanta: ");
                    offender.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko policjanta: ");
                    offender.setLastName(scanner.next());
                    System.out.println("Podaj pensje policjanta: ");
                    offender.setSalary(scanner.nextFloat());

                    g.V().hasLabel("Offender")
                        .property("id", offender.getId())
                        .property("firstName", offender.getFirstName())
                        .property("lastName", offender.getLastName())
                        .property("salary", offender.getSalary())
                        .iterate();

                    System.out.println("Zaktualizowano policjanta: " + offender + "\n");
                    break;
                case 2:
                    Victim victim = new Victim();
                    System.out.println("Podaj id poszkodowanego do zaktualizowania: ");
                    victim.setId(scanner.next());
                    System.out.println("Aktualizacja poszkodowanego w bazie danych.");
                    System.out.println("Podaj imię poszkodowanego: ");
                    victim.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko poszkodowanego: ");
                    victim.setLastName(scanner.next());
                    System.out.println("Podaj adres poszkodowanego: ");
                    victim.setAddress(scanner.next());

                    g.V().hasLabel("Victim")
                        .property("id", victim.getId())
                        .property("firstName", victim.getFirstName())
                        .property("lastName", victim.getLastName())
                        .property("address", victim.getAddress())
                        .iterate();

                    System.out.println("Zaktualizowano poszkodowanego: " + victim + "\n");
                    break;
                case 3:
                    Crime crime = new Crime();
                    System.out.println("Podaj id przestępstwa do zaktualizowania: ");
                    crime.setId(scanner.next());
                    System.out.println("Aktualizacja przestępstwa w bazie danych.");
                    System.out.println("Podaj id policjanta: ");
                    crime.setIdOffender(scanner.next());
                    System.out.println("Podaj id poszkodowanego: ");
                    crime.setIdVictim(scanner.next());
                    System.out.println("Podaj typ przestępstwa: ");
                    crime.setCrimeType(scanner.next());
                    System.out.println("Podaj datę przestępstwa: ");
                    crime.setCrimeDate(scanner.next());

                    g.V().hasLabel("Crime")
                        .property("id", crime.getId())
                        .property("idOffender", crime.getIdOffender())
                        .property("idVictim", crime.getIdVictim())
                        .property("crimeType", crime.getCrimeType())
                        .property("crimeDate", crime.getCrimeDate())
                        .next();

                    System.out.println("Zaktualizowano przestępstwo: " + crime + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void deleteOperation(GraphTraversalSource g) {
        while (true) {
            System.out.println("Co chciałbyś usunąć z bazy danych ?");
            System.out.print("1 - Policjanta \n2 - Poszkodowanego \n3 - Przestępstwo \n" +
                    "default - zakończenie operacji usuwania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj id policjanta do usunięcia: ");
                    String offenderId = scanner.next();
                    g.V().hasLabel("Offender").has("id", offenderId).drop().iterate();
                    System.out.println("Usunięto policjanta o id: " + offenderId + "\n");
                    break;
                case 2:
                    System.out.println("Podaj id poszkodowanego do usunięcia: ");
                    String victimId = scanner.next();
                    g.V().hasLabel("Victim").has("id", victimId).drop().iterate();
                    System.out.println("Usunięto poszkodowanego o id: " + victimId + "\n");
                    break;
                case 3:
                    System.out.println("Podaj id przestępstwa do usunięcia: ");
                    String crimeId = scanner.next();
                    g.V().hasLabel("Crime").has("id", crimeId).drop().iterate();
                    System.out.println("Usunięto przestępstwo o id: " + crimeId + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void getOperation(GraphTraversalSource g) {
        while (true) {
            System.out.println("Co chciałbyś pobrać z bazy danych ?");
            System.out.print("1 - Policjanta \n2 - Poszkodowanego \n3 - Przestępstwo \n" +
                    "default - zakończenie operacji pobierania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Pobieranie policjanta z bazy danych po: \n1 - Id \n2 - Imieniu");
                    int clientSelectedOption = scanner.nextInt();
                    switch (clientSelectedOption) {
                        case 1:
                            System.out.println("Podaj id policjanta: ");
                            System.out.println("Znaleziono policjanta: Offender: " + g.V().hasLabel("Offender")
                                .has("id", scanner.next()).valueMap().toList());
                            break;
                        case 2:
                            System.out.println("Podaj imię policjanata: ");
                            System.out.println("Znaleziono policjanta: Offender: " + g.V().hasLabel("Offender")
                                .has("firstName", scanner.next()).valueMap().toList());
                            break;
                        default: return;
                    }
                    break;
                case 2:
                    System.out.println("Pobieranie poszkodowanego z bazy danych po: \n1 - Id \n2 - Nazwisku");
                    int placeSelectedOption = scanner.nextInt();
                    switch (placeSelectedOption) {
                        case 1:
                            System.out.println("Podaj id poszkodowanego: ");
                            System.out.println("Znaleziono poszkodowanego: Victim: " + g.V().hasLabel("Victim")
                                .has("id", scanner.next()).valueMap().toList());
                            break;
                        case 2:
                            System.out.println("Podaj nazwisko poszkodowanego: ");
                            System.out.println("Znaleziono poszkodowanego: Victim: " + g.V().hasLabel("Victim")
                                .has("lastName", scanner.next()).valueMap().toList());
                            break;
                        default: return;
                    }
                    break;
                case 3:
                    System.out.println("Pobieranie przestępstwa z bazy danych po: \n1 - Id \n2 - Typie przestępstwa");
                    int travelSelectedOption = scanner.nextInt();
                    switch (travelSelectedOption) {
                        case 1:
                            System.out.println("Podaj id przestępstwa: ");
                            System.out.println("Znaleziono przestępstwo: Crime: " + g.V().hasLabel("Crime")
                                .has("id", scanner.next()).valueMap().toList());
                            break;
                        case 2:
                            System.out.println("Podaj typ przestępstwa: ");
                            System.out.println("Znaleziono przestępstwo: Crime: "  + g.V().hasLabel("Crime")
                                .has("crimeType", scanner.next()).valueMap().toList());
                            break;
                        default: return;
                    }
                    break;
                default: return;
            }
        }
    }

    public static void dataProcessing(GraphTraversalSource g) {
        System.out.println("Obliczanie sumy wypłat wszystkich policjantów");
        float sum = 0;
        var valueMapList = g.V().hasLabel("Offender").valueMap().toList();
        for (var valueMap: valueMapList) {
            List<Float> sumList = (List<Float>) valueMap.get("salary");
            sum +=  sumList.stream().findFirst().get();
        }
        System.out.println("Suma wszystkich wypłat policjantów: " + sum);
    }

    public static void main (String[]args) throws Exception {
        Graph graph = TinkerFactory.createModern();
        GraphTraversalSource g = graph.traversal();
        System.out.println("Połączono z bazą JanusGraph !");
        while (true) {
            System.out.println("Witamy w systemie policji!");
            System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                    + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
            System.out.println("Proszę wybrać pożądaną operacje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    saveOperation(g);
                    break;
                case 2:
                    updateOperation(g);
                    break;
                case 3:
                    deleteOperation(g);
                    break;
                case 4:
                    getOperation(g);
                    break;
                case 5:
                    dataProcessing(g);
                    break;
                default:
                    graph.close();
                    g.close();
                    return;
            }
        }

    }
}

/*

    public static void dodaj(GraphTraversalSource g){
        System.out.println("Podaj nazwisko kierowcy");
        Scanner scan=new Scanner(System.in);
        String nazwisko=scan.next();
        System.out.println("Podaj imię kierowcy");
        String imie=scan.next();
        System.out.println("Podaj wiek kierowcy");
        int wiek=scan.nextInt();
        System.out.println("Podaj wynagrodzenie");
        int wynagrodzenie=scan.nextInt();
        g.addV("Kierowcy").property("Nazwisko",nazwisko).property("Imie",imie).property("wiek",wiek).property("wynagrodzenie",wynagrodzenie).next();
    }
    public static void wyświetl(GraphTraversalSource g){
        System.out.println(g.V().hasLabel("Kierowcy").valueMap().toList());
    }
    public static void modyfikuj(GraphTraversalSource g){
        System.out.println("Podaj nazwisko");
        Scanner scan=new Scanner(System.in);
        String nazwisko=scan.next();
        System.out.println("Podaj imie");
        String imie=scan.next();
        System.out.println("1- zmiana nazwiska");
        System.out.println("2- zmiana imienia");
        System.out.println("3- zmiana wieku");
        System.out.println("4- zmiana wynagrodzenia");
        int wybor=scan.nextInt();
        switch (wybor){
            case 1:
                System.out.println("Podaj nowe nazwisko");
                String n_nazwisko=scan.next();
                g.V().has("Nazwisko",nazwisko).has("Imie",imie).property("Nazwisko",n_nazwisko).iterate();
                break;
            case 2:
                System.out.println("Podaj nowe imie");
                String n_imie=scan.next();
                g.V().has("Nazwisko",nazwisko).has("Imie",imie).property("Imie",n_imie).iterate();
                break;
            case 3:
                System.out.println("Podaj nowy wiek");
                int n_wiek=scan.nextInt();
                g.V().has("Nazwisko",nazwisko).has("Imie",imie).property("wiek",n_wiek).iterate();
                break;
            case 4:
                System.out.println("Podaj nowe wynagrodzenie");
                int n_wynagrodzenie=scan.nextInt();
                g.V().has("Nazwisko",nazwisko).has("Imie",imie).property("wynagrodzenie",n_wynagrodzenie).iterate();
                break;
        }
    }
    public static void usun(GraphTraversalSource g){
        wyświetl(g);
        System.out.println("Podaj nazwisko");
        Scanner scan=new Scanner(System.in);
        String nazwisko=scan.next();
        System.out.println("Podaj imie");
        String imie=scan.next();
        g.V().has("Nazwisko",nazwisko).has("Imie",imie).drop().iterate();
    }
    public static void wyszukaj(GraphTraversalSource g){
        Scanner scan=new Scanner(System.in);
        System.out.println("1-po nazwisku");
        System.out.println("2- po imieniu");
        System.out.println("3 - po wieku");
        System.out.println("4 - po wynagrodzeniu");
        int wybor =scan.nextInt();
        switch (wybor){
            case 1:
                System.out.println("Podaj nazwisko");
                String naz=scan.next();
                System.out.println("wynik wyszukiwania");
                System.out.println(g.V().has("Nazwisko",naz).valueMap().toList());
                break;
            case 2:
                System.out.println("Podaj imie");
                String imie=scan.next();
                System.out.println("wynik wyszukiwania");
                System.out.println(g.V().has("Imie",imie).valueMap().toList());
                break;
            case 3:
                System.out.println("Podaj wiek minimalny");
                int wiek_min=scan.nextInt();
                System.out.println("Podaj wiek maksymalny");
                int wiek_maks=scan.nextInt();
                System.out.println("wynik wyszukiwania");
                System.out.println(g.V().has("wiek", P.gt(wiek_min)).has("wiek",P.lt(wiek_maks)).valueMap().toList());
                break;
            case 4:
                System.out.println("Podaj wypłate minimalną");
                int wyp_min=scan.nextInt();
                System.out.println("Podaj wynagrodzenie maksymalne");
                int wyp_maks=scan.nextInt();
                System.out.println("wynik wyszukiwania");
                System.out.println(g.V().has("wynagrodzenie", P.gt(wyp_min)).has("wynagrodzenie",P.lt(wyp_maks)).valueMap().toList());
                break;
        }
    }
    public  static void drop(GraphTraversalSource g){
        g.V().hasLabel("Kierowcy").drop().iterate();
    }
    public static void main (String[]args) throws Exception {
        Graph graph = TinkerFactory.createModern();
        GraphTraversalSource g = graph.traversal();
        boolean test=true;
        Scanner scan=new Scanner(System.in);
        while (test){
            System.out.println("1- dodawanie kierowców");
            System.out.println("2- wyświetlanie kierowców");
            System.out.println("3- modyfikacja");
            System.out.println("4- usuwanie");
            System.out.println("5- Wyszukiwanie");
            System.out.println("6- Czyszczenie bazy");
            int wybor=scan.nextInt();
            switch (wybor) {
                case 1:
                    dodaj(g);
                    break;
                case 2:
                    wyświetl(g);
                    break;
                case 3:
                    modyfikuj(g);
                    break;
                case 4:
                    usun(g);
                    break;
                case 5:
                    wyszukaj(g);
                    break;
                case 6:
                    drop(g);
                    break;
            }
        }
    }*/
