import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import com.hazelcast.map.IMap;
import com.hazelcast.partition.MigrationListener;
import com.hazelcast.partition.MigrationState;
import com.hazelcast.partition.PartitionService;
import com.hazelcast.partition.ReplicaMigrationEvent;

public class ApplicationClient {

    public static void saveOperation(HazelcastInstance instance) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zapisać w bazie danych ?");
            System.out.print("1 - Kierowce \n2 - Samochód \n3 - Pasażera \n4 - Przejazd \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");

            final Random rnd = new Random(System.currentTimeMillis());
            Long key = (long) Math.abs(rnd.nextInt());
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    Driver driver = new Driver();
                    System.out.println("Zapis kierowcy do bazy danych.");
                    System.out.println("Podaj imię kierowcy: ");
                    driver.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko kierowcy: ");
                    driver.setLastName(scanner.next());
                    System.out.println("Podaj wiek kierowcy: ");
                    driver.setAge(scanner.nextInt());
                    System.out.println("Podaj pensje kierowcy: ");
                    driver.setSalary(scanner.nextFloat());

                    Map<Long, Driver> drivers = instance.getMap("drivers");
                    drivers.put(key, driver);
                    System.out.println("Zapisano kierowcę o kluczu: " + key + " oraz wartościach: " + driver + "\n");
                    break;
                case 2:
                    Car car = new Car();
                    System.out.println("Zapis samochodu do bazy danych.");
                    System.out.println("Podaj marke samochodu: ");
                    car.setBrand(scanner.next());
                    System.out.println("Podaj model samochodu: ");
                    car.setModel(scanner.next());
                    System.out.println("Podaj rok produkcji samochodu: ");
                    car.setProductionYear(scanner.nextInt());
                    System.out.println("Podaj przebieg samochodu: ");
                    car.setMileage(scanner.nextInt());

                    Map<Long, Car> cars = instance.getMap("cars");
                    cars.put(key, car);
                    System.out.println("Zapisano samochód o kluczu: " + key + " oraz wartościach: " + car + "\n");
                    break;
                case 3:
                    Passenger passenger = new Passenger();
                    System.out.println("Zapis pasażera do bazy danych.");
                    System.out.println("Podaj imię pasażera: ");
                    passenger.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko pasażera: ");
                    passenger.setLastName(scanner.next());
                    System.out.println("Podaj adres pasażera: ");
                    passenger.setAddress(scanner.next());

                    Map<Long, Passenger> passangers = instance.getMap("passengers");
                    passangers.put(key, passenger);
                    System.out.println("Zapisano pasażera o kluczu: " + key + " oraz wartościach: " + passenger + "\n");
                    break;
                case 4:
                    Transport transport = new Transport();
                    System.out.println("Zapis przejazdu do bazy danych.");
                    System.out.println("Podaj id kierowcy: ");
                    transport.setDriverId(scanner.nextLong());
                    System.out.println("Podaj id pasażera: ");
                    transport.setPassengerId(scanner.nextLong());
                    System.out.println("Podaj koszt przejazdu: ");
                    transport.setTransportationCost(scanner.nextFloat());

                    Map<Long, Transport> transports = instance.getMap("transports");
                    transports.put(key, transport);
                    System.out.println("Zapisano przejazd o kluczu: " + key + " oraz wartościach: " + transport + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void updateOperation(HazelcastInstance instance) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zaktualizować w bazie danych ?");
            System.out.print("1 - Kierowce \n2 - Samochód \n3 - Pasażera \n4 - Przejazd \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            long key;

            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj klucz kierowcy do zaktualizowania: ");
                    key = scanner.nextLong();

                    Driver driver = new Driver();
                    System.out.println("Aktualizacja kierowcy w bazie danych.");
                    System.out.println("Podaj imię kierowcy: ");
                    driver.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko kierowcy: ");
                    driver.setLastName(scanner.next());
                    System.out.println("Podaj wiek kierowcy: ");
                    driver.setAge(scanner.nextInt());
                    System.out.println("Podaj pensje kierowcy: ");
                    driver.setSalary(scanner.nextFloat());

                    IMap<Long, Driver> drivers = instance.getMap("drivers");
                    drivers.remove(key);
                    drivers.set(key, driver);
                    System.out.println("Zaktualizowano kierowcę o kluczu: " + key + " oraz wartościach: " + driver + "\n");
                    break;
                case 2:
                    System.out.println("Podaj klucz samochodu do zaktualizowania: ");
                    key = scanner.nextLong();

                    Car car = new Car();
                    System.out.println("Aktualizacja samochodu w bazie danych.");
                    System.out.println("Podaj marke samochodu: ");
                    car.setBrand(scanner.next());
                    System.out.println("Podaj model samochodu: ");
                    car.setModel(scanner.next());
                    System.out.println("Podaj rok produkcji samochodu: ");
                    car.setProductionYear(scanner.nextInt());
                    System.out.println("Podaj przebieg samochodu: ");
                    car.setMileage(scanner.nextInt());

                    IMap<Long, Car> cars = instance.getMap("cars");
                    cars.remove(key);
                    cars.set(key, car);
                    System.out.println("Zaktualizowano samochód o kluczu: " + key + " oraz wartościach: " + car + "\n");
                    break;
                case 3:
                    System.out.println("Podaj klucz pasażera do zaktualizowania: ");
                    key = scanner.nextLong();

                    Passenger passenger = new Passenger();
                    System.out.println("Aktualizacja pasażera w bazie danych.");
                    System.out.println("Podaj imię pasażera: ");
                    passenger.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko pasażera: ");
                    passenger.setLastName(scanner.next());
                    System.out.println("Podaj adres pasażera: ");
                    passenger.setAddress(scanner.next());

                    IMap<Long, Passenger> passengers = instance.getMap("passengers");
                    passengers.remove(key);
                    passengers.set(key, passenger);
                    System.out.println("Aktualizacja pasażera o kluczu: " + key + " oraz wartościach: " + passenger + "\n");
                    break;
                case 4:
                    System.out.println("Podaj klucz przejazdu do zaktualizowania: ");
                    key = scanner.nextLong();

                    Transport transport = new Transport();
                    System.out.println("Aktualizacja przejazdu w bazie danych.");
                    System.out.println("Podaj id kierowcy: ");
                    transport.setDriverId(scanner.nextLong());
                    System.out.println("Podaj id pasażera: ");
                    transport.setPassengerId(scanner.nextLong());
                    System.out.println("Podaj koszt przejazdu: ");
                    transport.setTransportationCost(scanner.nextFloat());

                    IMap<Long, Transport> transports = instance.getMap("transports");
                    transports.remove(key);
                    transports.set(key, transport);
                    System.out.println("Zaktualizowano przejazd o kluczu: " + key + " oraz wartościach: " + transport + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void deleteOperation(HazelcastInstance instance) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś usunąć z bazy danych ?");
            System.out.print("1 - Kierowce \n2 - Samochód \n3 - Pasażera \n4 - Przejazd \n" +
                    "default - zakończenie operacji usuwania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            long key;
            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj klucz kierowcy do usunięcia: ");
                    IMap<Long, Driver> drivers = instance.getMap("drivers");
                    key = scanner.nextLong();
                    drivers.remove(key);
                    System.out.println("Usunięto kierowcę o kluczu: " + key + "\n");
                    break;
                case 2:
                    System.out.println("Podaj klucz samochodu do usunięcia: ");
                    IMap<Long, Car> cars = instance.getMap("cars");
                    key = scanner.nextLong();
                    cars.remove(key);
                    System.out.println("Usunięto samochód o kluczu: " + key + "\n");
                    break;
                case 3:
                    System.out.println("Podaj klucz pasażera do usunięcia: ");
                    IMap<Long, Passenger> passengers = instance.getMap("passengers");
                    key = scanner.nextLong();
                    passengers.remove(key);
                    System.out.println("Usunięto pasażera o kluczu: " + key + "\n");
                    break;
                case 4:
                    System.out.println("Podaj klucz przejazdu do usunięcia: ");
                    IMap<Long, Transport> transports = instance.getMap("transports");
                    key = scanner.nextLong();
                    transports.remove(key);
                    System.out.println("Usunięto przejazd o kluczu: " + key + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void getOperation(HazelcastInstance instance) {
        IMap<Long, Driver> drivers = instance.getMap("drivers");
        IMap<Long, Car> cars = instance.getMap("cars");
        IMap<Long, Passenger> passengers = instance.getMap("passengers");
        IMap<Long, Transport> transports = instance.getMap("transports");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś pobrać z bazy danych ?");
            System.out.print("1 - Kierowce \n2 - Samochód \n3 - Pasażera \n4 - Przejazd \n" +
                    "default - zakończenie operacji pobierania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    System.out.println("Pobieranie kierowcy z bazy danych po: \n1 - Kluczu \n2 - Imieniu");
                    int driverSelectedOption = scanner.nextInt();
                    switch (driverSelectedOption) {
                        case 1:
                            System.out.println("Podaj klucz kierowcy: ");
                            long key = scanner.nextLong();
                            for (Entry<Long, Driver> e : drivers.entrySet())
                                if(e.getKey().equals(key))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        case 2:
                            System.out.println("Podaj imie kierowcy: ");
                            String firstName = scanner.next();
                            for (Entry<Long, Driver> e : drivers.entrySet())
                                if(e.getValue().getFirstName().equals(firstName))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        default:
                            return;
                    }
                    break;
                case 2:
                    System.out.println("Pobieranie samochodu z bazy danych po: \n1 - Kluczu \n2 - Marce");
                    int carSelectedOption = scanner.nextInt();
                    switch (carSelectedOption) {
                        case 1:
                            System.out.println("Podaj klucz samochodu: ");
                            long key = scanner.nextLong();
                            for (Entry<Long, Car> e : cars.entrySet())
                                if(e.getKey().equals(key))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        case 2:
                            System.out.println("Podaj marke samochodu: ");
                            String brand = scanner.next();
                            for (Entry<Long, Car> e : cars.entrySet())
                                if(e.getValue().getBrand().equals(brand))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        default:
                            return;
                    }
                    break;
                case 3:
                    System.out.println("Pobieranie pasażera z bazy danych po: \n1 - Kluczu \n2 - Imieniu");
                    int passengerSelectedOption = scanner.nextInt();
                    switch (passengerSelectedOption) {
                        case 1:
                            System.out.println("Podaj klucz pasażera: ");
                            long key = scanner.nextLong();
                            for (Entry<Long, Passenger> e : passengers.entrySet())
                                if(e.getKey().equals(key))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        case 2:
                            System.out.println("Podaj imie pasażera: ");
                            String firstName = scanner.next();
                            for (Entry<Long, Passenger> e : passengers.entrySet())
                                if(e.getValue().getFirstName().equals(firstName))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        default:
                            return;
                    }
                    break;
                case 4:
                    System.out.println("Pobieranie przejazdu z bazy danych po: \n1 - Kluczu \n2 - koszcie przejazdu");
                    int transportSelectedOption = scanner.nextInt();
                    switch (transportSelectedOption) {
                        case 1:
                            System.out.println("Podaj klucz przejazdu: ");
                            long key = scanner.nextLong();
                            for (Entry<Long, Transport> e : transports.entrySet())
                                if(e.getKey().equals(key))
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        case 2:
                            System.out.println("Podaj koszt przejazdu: ");
                            float transportationCost = scanner.nextFloat();
                            for (Entry<Long, Transport> e : transports.entrySet())
                                if(e.getValue().getTransportationCost() == transportationCost)
                                    System.out.println(e.getKey() + " " + e.getValue());
                            break;
                        default:
                            return;
                    }
                    break;
                default: return;
            }
        }
    }

    public static void dataProcessing(HazelcastInstance instance) {
        System.out.println("Obliczanie sumy kosztów wszystkich przejazdów");
        IMap<Long, Transport> transports = instance.getMap("transports");
        float sum = 0;
        for (Entry<Long, Transport> e : transports.entrySet())
            sum += e.getValue().getTransportationCost();

        System.out.println("Suma kosztów wszystkich przejazdów: " + sum);
    }

    public static void main(String[] args) throws UnknownHostException {
        Config config = HConfig.getConfig();
        Scanner scanner = new Scanner(System.in);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        IExecutorService executorService = instance.getExecutorService("exec");
        while (true) {
            System.out.println("Witamy w systemie firmy przewozowej!");
            System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                    + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
            System.out.println("Proszę wybrać pożądaną operacje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    saveOperation(instance);
                    break;
                case 2:
                    updateOperation(instance);
                    break;
                case 3:
                    deleteOperation(instance);
                    break;
                case 4:
                    getOperation(instance);
                    break;
                case 5:
                    dataProcessing(instance);
                    executorService.submitToAllMembers(new HCallable());
                    break;
                default:
                    instance.getLifecycleService().shutdown();
                    return;
            }
        }
    }
}
