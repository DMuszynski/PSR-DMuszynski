import org.apache.commons.lang3.SerializationUtils;
import org.example.Car;
import org.example.Driver;
import org.example.Passenger;
import org.example.Transport;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ApplicationClient {

    public static void saveOperation(Jedis instance) {
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

                    byte[] driverObj = SerializationUtils.serialize(driver);
                    instance.set(key.toString().getBytes(), driverObj);

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

                    byte[] carObj = SerializationUtils.serialize(car);
                    instance.set(key.toString().getBytes(), carObj);

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

                    byte[] passengerObj = SerializationUtils.serialize(passenger);
                    instance.set(key.toString().getBytes(), passengerObj);

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

                    byte[] transportObj = SerializationUtils.serialize(transport);
                    instance.set(key.toString().getBytes(), transportObj);

                    System.out.println("Zapisano przejazd o kluczu: " + key + " oraz wartościach: " + transport + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void updateOperation(Jedis instance) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś zaktualizować w bazie danych ?");
            System.out.print("1 - Kierowce \n2 - Samochód \n3 - Pasażera \n4 - Przejazd \n" +
                    "default - zakończenie operacji zapisu do bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            Long key;

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

                    byte[] driverObj = SerializationUtils.serialize(driver);
                    instance.set(key.toString().getBytes(), driverObj);

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

                    byte[] carObj = SerializationUtils.serialize(car);
                    instance.set(key.toString().getBytes(), carObj);

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

                    byte[] passengerObj = SerializationUtils.serialize(passenger);
                    instance.set(key.toString().getBytes(), passengerObj);

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

                    byte[] transportObj = SerializationUtils.serialize(transport);
                    instance.set(key.toString().getBytes(), transportObj);

                    System.out.println("Zaktualizowano przejazd o kluczu: " + key + " oraz wartościach: " + transport + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void deleteOperation(Jedis instance) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chciałbyś usunąć z bazy danych ?");
            System.out.print("1 - Kierowce \n2 - Samochód \n3 - Pasażera \n4 - Przejazd \n" +
                    "default - zakończenie operacji usuwania z bazy danych\n");
            System.out.println("Proszę wybrać pożądaną opcje: ");
            int selectedOption = scanner.nextInt();
            Long key;
            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj klucz kierowcy do usunięcia: ");
                    key = scanner.nextLong();
                    instance.del(key.toString().getBytes());
                    System.out.println("Usunięto kierowcę o kluczu: " + key + "\n");
                    break;
                case 2:
                    System.out.println("Podaj klucz samochodu do usunięcia: ");
                    key = scanner.nextLong();
                    instance.del(key.toString().getBytes());
                    System.out.println("Usunięto samochód o kluczu: " + key + "\n");
                    break;
                case 3:
                    System.out.println("Podaj klucz pasażera do usunięcia: ");
                    key = scanner.nextLong();
                    instance.del(key.toString().getBytes());
                    System.out.println("Usunięto pasażera o kluczu: " + key + "\n");
                    break;
                case 4:
                    System.out.println("Podaj klucz przejazdu do usunięcia: ");
                    key = scanner.nextLong();
                    instance.del(key.toString().getBytes());
                    System.out.println("Usunięto przejazd o kluczu: " + key + "\n");
                    break;
                default: return;
            }
        }
    }

    public static void getOperation(Jedis instance) {
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
                            Long key = scanner.nextLong();
                            Object driverObj = SerializationUtils.deserialize(instance.get(key.toString().getBytes()));
                            System.out.println(key + " " + (Driver) driverObj);
                            break;
                        case 2:
                            System.out.println("Podaj imie kierowcy: ");
                            String firstName = scanner.next();
                            Set<String> keys = instance.keys("*");
                            for (String k : keys) {
                                Object driverObj2 = SerializationUtils.deserialize(instance.get(k.getBytes()));
                                if (driverObj2 instanceof Driver) {
                                    if (((Driver) driverObj2).getFirstName().equals(firstName)) {
                                        System.out.println(k + " " + (Driver) driverObj2);
                                        break;
                                    }
                                }
                            }
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
                            Long key = scanner.nextLong();
                            Object carObj = SerializationUtils.deserialize(instance.get(key.toString().getBytes()));
                            System.out.println(key + " " + (Car) carObj);
                            break;
                        case 2:
                            System.out.println("Podaj marke samochodu: ");
                            String brand = scanner.next();
                            Set<String> keys = instance.keys("*");
                            for (String k : keys) {
                                Object carObj2 = SerializationUtils.deserialize(instance.get(k.getBytes()));
                                if (carObj2 instanceof Car) {
                                    if (((Car) carObj2).getBrand().equals(brand)) {
                                        System.out.println(k + " " + (Car) carObj2);
                                        break;
                                    }
                                }
                            }
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
                            Long key = scanner.nextLong();
                            Object passengerObj = SerializationUtils.deserialize(instance.get(key.toString().getBytes()));
                            System.out.println(key + " " + (Passenger) passengerObj);
                            break;
                        case 2:
                            System.out.println("Podaj imie pasażera: ");
                            String firstName = scanner.next();
                            Set<String> keys = instance.keys("*");
                            for (String k : keys) {
                                Object passengerObj2 = SerializationUtils.deserialize(instance.get(k.getBytes()));
                                if (passengerObj2 instanceof Passenger) {
                                    if (((Passenger) passengerObj2).getFirstName().equals(firstName)) {
                                        System.out.println(k + " " + (Passenger) passengerObj2);
                                        break;
                                    }
                                }
                            }
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
                            Long key = scanner.nextLong();
                            Object transportObj = SerializationUtils.deserialize(instance.get(key.toString().getBytes()));
                            System.out.println(key + " " + (Passenger) transportObj);
                            break;
                        case 2:
                            System.out.println("Podaj koszt przejazdu: ");
                            float transportationCost = scanner.nextFloat();
                            Set<String> keys = instance.keys("*");
                            for (String k : keys) {
                                Object transportObj2 = SerializationUtils.deserialize(instance.get(k.getBytes()));
                                if (transportObj2 instanceof Transport) {
                                    if (((Transport) transportObj2).getTransportationCost() == (transportationCost)) {
                                        System.out.println(k + " " + (Transport) transportObj2);
                                        break;
                                    }
                                }
                            }
                            break;
                        default:
                            return;
                    }
                    break;
                default: return;
            }
        }
    }

    public static void dataProcessing(Jedis instance) {
        System.out.println("Obliczanie sumy kosztów wszystkich przejazdów");
        Set<String> keys = instance.keys("*");

        float sum = 0;
        for (String key : keys) {
            Object obj = SerializationUtils.deserialize(instance.get(key.getBytes()));
            if (obj instanceof Transport) {
                Transport transport = (Transport) obj;
                sum += transport.getTransportationCost();
            }
        }
        System.out.println("Suma kosztów wszystkich przejazdów: " + sum);
    }

    public static void main(String[] args) {
        Jedis instance = new Jedis("127.0.0.1", 6379);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Połączono z serwerem Redis !");

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
                    break;
                default:
                    instance.shutdown();
                    return;
            }
        }
    }
}
