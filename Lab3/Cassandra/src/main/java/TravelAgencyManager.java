import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;
import com.datastax.oss.driver.api.querybuilder.update.Update;

import java.util.Random;
import java.util.Scanner;

public class TravelAgencyManager extends SimpleManager {
    final Scanner scanner = new Scanner(System.in);

    public TravelAgencyManager(CqlSession session) {
        super(session);
    }

    public void createTable() {
        CreateTable createClientTable = SchemaBuilder.createTable("Client")
            .withPartitionKey("id", DataTypes.BIGINT)
            .withColumn("firstName", DataTypes.TEXT)
            .withColumn("lastName", DataTypes.TEXT)
            .withColumn("address", DataTypes.TEXT);

        session.execute(createClientTable.build());

        CreateTable createPlaceTable = SchemaBuilder.createTable("Place")
                .withPartitionKey("id", DataTypes.BIGINT)
                .withColumn("country", DataTypes.TEXT)
                .withColumn("city", DataTypes.TEXT)
                .withColumn("address", DataTypes.TEXT);

        session.execute(createPlaceTable.build());

        CreateTable createTravelTable = SchemaBuilder.createTable("Travel")
                .withPartitionKey("id", DataTypes.BIGINT)
                .withColumn("clientId", DataTypes.BIGINT)
                .withColumn("placeId", DataTypes.BIGINT)
                .withColumn("numberOfDays", DataTypes.INT)
                .withColumn("price", DataTypes.FLOAT);

        session.execute(createTravelTable.build());
    }

    public void saveOperation() {
        while (true) {
            System.out.println("Co chcia??by?? zapisa?? w bazie danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podr????y \n3 - Podr???? \n" +
                    "default - zako??czenie operacji zapisu do bazy danych\n");
            System.out.println("Prosz?? wybra?? po????dan?? opcje: ");

            final Random rnd = new Random(System.currentTimeMillis());
            long id = Math.abs(rnd.nextInt());
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    Client client = new Client();
                    client.setId(id);
                    System.out.println("Zapis klienta do bazy danych.");
                    System.out.println("Podaj imi?? klienta: ");
                    client.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko klienta: ");
                    client.setLastName(scanner.next());
                    System.out.println("Podaj adres klienta: ");
                    client.setAddress(scanner.next());

                    Insert insertClient = QueryBuilder.insertInto("TravelAgency", "Client")
                        .value("id", QueryBuilder.raw(String.valueOf(client.getId())))
                        .value("firstName", QueryBuilder.raw("'"+client.getFirstName()+"'"))
                        .value("lastName", QueryBuilder.raw("'"+ client.getLastName()+"'"))
                        .value("address", QueryBuilder.raw("'"+client.getAddress()+"'"));

                    session.execute(insertClient.build());
                    System.out.println("Zapisano klienta: " + client + "\n");
                    break;
                case 2:
                    Place place = new Place();
                    place.setId(id);
                    System.out.println("Zapis miejsca podr????y do bazy danych.");
                    System.out.println("Podaj kraj podr????y: ");
                    place.setCountry(scanner.next());
                    System.out.println("Podaj miasto podr????y: ");
                    place.setCity(scanner.next());
                    System.out.println("Podaj adres podr????y: ");
                    place.setAddress(scanner.next());

                    Insert insertPlace = QueryBuilder.insertInto("TravelAgency", "Place")
                        .value("id", QueryBuilder.raw(String.valueOf(place.getId())))
                        .value("country", QueryBuilder.raw("'"+place.getCountry()+"'"))
                        .value("city", QueryBuilder.raw("'"+place.getCity()+"'"))
                        .value("address", QueryBuilder.raw("'"+place.getAddress()+"'"));

                    session.execute(insertPlace.build());
                    System.out.println("Zapisano miejsce podr????y: " + place + "\n");
                    break;
                case 3:
                    Travel travel = new Travel();
                    travel.setId(id);
                    System.out.println("Zapis podr????y do bazy danych.");
                    System.out.println("Podaj id klienta biura podr????y: ");
                    travel.setClientId(scanner.nextLong());
                    System.out.println("Podaj id miejsca podr????y: ");
                    travel.setPlaceId(scanner.nextLong());
                    System.out.println("Podaj ilo???? dni trwania podr????y: ");
                    travel.setNumberOfDays(scanner.nextInt());
                    System.out.println("Podaj koszt podr????y: ");
                    travel.setPrice(scanner.nextFloat());

                    Insert insertTravel = QueryBuilder.insertInto("TravelAgency", "Travel")
                        .value("id", QueryBuilder.raw(String.valueOf(travel.getId())))
                        .value("clientId", QueryBuilder.raw(String.valueOf(travel.getClientId())))
                        .value("placeId", QueryBuilder.raw(String.valueOf(travel.getPlaceId())))
                        .value("numberOfDays", QueryBuilder.raw(String.valueOf(travel.getNumberOfDays())))
                        .value("price", QueryBuilder.raw(String.valueOf(travel.getPrice())));

                    session.execute(insertTravel.build());
                    System.out.println("Zapisano podr????: " + travel + "\n");
                    break;
                default: return;
            }
        }
    }

    public void updateOperation() {
        while (true) {
            System.out.println("Co chcia??by?? zaktualizowa?? w bazie danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podr????y \n3 - Podr???? \n" +
                    "default - zako??czenie operacji aktualizacji w bazie danych\n");
            System.out.println("Prosz?? wybra?? po????dan?? opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    Client client = new Client();
                    System.out.println("Podaj id klienta do zaktualizowania: ");
                    client.setId(scanner.nextLong());
                    System.out.println("Aktualizacja klienta w bazie danych.");
                    System.out.println("Podaj imi?? klienta: ");
                    client.setFirstName(scanner.next());
                    System.out.println("Podaj nazwisko klienta: ");
                    client.setLastName(scanner.next());
                    System.out.println("Podaj adres klienta: ");
                    client.setAddress(scanner.next());

                    Update updateClient = QueryBuilder.update("Client")
                        .setColumn("firstName", QueryBuilder.literal(client.getFirstName()))
                        .setColumn("lastName", QueryBuilder.literal(client.getLastName()))
                        .setColumn("address", QueryBuilder.literal(client.getAddress()))
                        .whereColumn("id").isEqualTo(QueryBuilder.literal(client.getId()));

                    session.execute(updateClient.build());
                    System.out.println("Zaktualizowano klienta: " + client + "\n");
                    break;
                case 2:
                    Place place = new Place();
                    System.out.println("Podaj id miejsca podr????y do zaktualizowania: ");
                    place.setId(scanner.nextLong());
                    System.out.println("Aktualizacja miejsca podr????y w bazie danych.");
                    System.out.println("Podaj kraj podr????y: ");
                    place.setCountry(scanner.next());
                    System.out.println("Podaj miasto podr????y: ");
                    place.setCity(scanner.next());
                    System.out.println("Podaj adres podr????y: ");
                    place.setAddress(scanner.next());

                    Update updatePlace = QueryBuilder.update("Place")
                        .setColumn("country", QueryBuilder.literal(place.getCountry()))
                        .setColumn("city", QueryBuilder.literal(place.getCity()))
                        .setColumn("address", QueryBuilder.literal(place.getAddress()))
                        .whereColumn("id").isEqualTo(QueryBuilder.literal(place.getId()));

                    session.execute(updatePlace.build());
                    System.out.println("Zaktualizowano miejsce podr????y: " + place + "\n");
                    break;
                case 3:
                    Travel travel = new Travel();
                    System.out.println("Podaj id podr????y do zaktualizowania: ");
                    travel.setId(scanner.nextLong());
                    System.out.println("Aktualizacja podr????y w bazie danych.");
                    System.out.println("Podaj id klienta biura podr????y: ");
                    travel.setClientId(scanner.nextLong());
                    System.out.println("Podaj id miejsca podr????y: ");
                    travel.setPlaceId(scanner.nextLong());
                    System.out.println("Podaj ilo???? dni trwania podr????y: ");
                    travel.setNumberOfDays(scanner.nextInt());
                    System.out.println("Podaj koszt podr????y: ");
                    travel.setPrice(scanner.nextFloat());

                    Update updateTravel = QueryBuilder.update("Travel")
                        .setColumn("clientId", QueryBuilder.literal(travel.getClientId()))
                        .setColumn("placeId", QueryBuilder.literal(travel.getPlaceId()))
                        .setColumn("numberOfDays", QueryBuilder.literal(travel.getNumberOfDays()))
                        .setColumn("price", QueryBuilder.literal(travel.getPrice()))
                        .whereColumn("id").isEqualTo(QueryBuilder.literal(travel.getId()));

                    session.execute(updateTravel.build());
                    System.out.println("Zaktualizowano podr????: " + travel + "\n");
                    break;
                default: return;
            }
        }
    }

    public void deleteOperation() {
        while (true) {
            System.out.println("Co chcia??by?? usun???? z bazy danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podr????y \n3 - Podr???? \n" +
                    "default - zako??czenie operacji usuwania z bazy danych\n");
            System.out.println("Prosz?? wybra?? po????dan?? opcje: ");

            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Podaj id klienta do usuni??cia: ");
                    long clientId = scanner.nextLong();
                    Delete deleteClient = QueryBuilder.deleteFrom("Client")
                        .whereColumn("id")
                        .isEqualTo(QueryBuilder.literal(clientId));
                    session.execute(deleteClient.build());
                    System.out.println("Usuni??to klienta o id: " + clientId + "\n");
                    break;
                case 2:
                    System.out.println("Podaj id miejsca podr????y do usuni??cia: ");
                    long placeId = scanner.nextLong();
                    Delete deletePlace = QueryBuilder.deleteFrom("Place")
                        .whereColumn("id")
                        .isEqualTo(QueryBuilder.literal(placeId));
                    session.execute(deletePlace.build());
                    System.out.println("Usuni??to miejsce podr????y o id: " + placeId + "\n");
                    break;
                case 3:
                    System.out.println("Podaj id podr????y do usuni??cia: ");
                    long travelId = scanner.nextLong();
                    Delete deleteTravel = QueryBuilder.deleteFrom("Travel")
                        .whereColumn("id")
                        .isEqualTo(QueryBuilder.literal(travelId));
                    session.execute(deleteTravel.build());
                    System.out.println("Usuni??to podr???? o id: " + travelId + "\n");
                    break;
                default: return;
            }
        }
    }

    public void getOperation() {
        while (true) {
            System.out.println("Co chcia??by?? pobra?? z bazy danych ?");
            System.out.print("1 - Klienta \n2 - Miejsce podr????y \n3 - Podr???? \n" +
                    "default - zako??czenie operacji pobierania z bazy danych\n");
            System.out.println("Prosz?? wybra?? po????dan?? opcje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    System.out.println("Pobieranie klienta z bazy danych po: \n1 - Id \n2 - Imieniu");
                    int clientSelectedOption = scanner.nextInt();
                    switch (clientSelectedOption) {
                        case 1:
                            System.out.println("Podaj id klienta: ");
                            Client client = new Client();
                            client.setId(scanner.nextLong());

                            String selectClientById = "SELECT * FROM Client WHERE id = " + client.getId() + ";";
                            Row clientRow = session.execute(selectClientById).one();
                            if (clientRow != null) {
                                client.setFirstName(clientRow.getString("firstname"));
                                client.setLastName(clientRow.getString("lastname"));
                                client.setAddress(clientRow.getString("address"));

                                System.out.println("Znaleziono klienta: " + client);
                            }
                            break;
                        case 2:
                            System.out.println("Podaj imi?? klienta: ");
                            Client client1 = new Client();
                            client1.setFirstName(scanner.next());

                            String selectClientByFirstName = "SELECT * FROM Client WHERE firstname = '" + client1.getFirstName() + "' ALLOW FILTERING;";
                            Row clientRow1 = session.execute(selectClientByFirstName).one();
                            if (clientRow1 != null) {
                                client1.setId(clientRow1.getLong("id"));
                                client1.setLastName(clientRow1.getString("lastname"));
                                client1.setAddress(clientRow1.getString("address"));

                                System.out.println("Znaleziono klienta: " + client1);
                            }
                            break;
                        default:
                            return;
                    }
                    break;
                case 2:
                    System.out.println("Pobieranie miejsca podr????y z bazy danych po: \n1 - Id \n2 - Kraju");
                    int placeSelectedOption = scanner.nextInt();
                    switch (placeSelectedOption) {
                        case 1:
                            System.out.println("Podaj id miejsca podr????y: ");
                            Place place = new Place();
                            place.setId(scanner.nextLong());

                            String selectPlaceById = "SELECT * FROM Place WHERE id = " + place.getId() + ";";
                            Row placeRow = session.execute(selectPlaceById).one();
                            if (placeRow != null) {
                                place.setCountry(placeRow.getString("country"));
                                place.setCity(placeRow.getString("city"));
                                place.setAddress(placeRow.getString("address"));

                                System.out.println("Znaleziono miejsce podr????y: " + place);
                            }
                            break;
                        case 2:
                            System.out.println("Podaj kraj miejsca podr????y: ");
                            Place place1 = new Place();
                            place1.setCountry(scanner.next());

                            String selectPlaceByCountry = "SELECT * FROM Place WHERE country = '" + place1.getCountry() + "' ALLOW FILTERING;";
                            Row placeRow1 = session.execute(selectPlaceByCountry).one();
                            if (placeRow1 != null) {
                                place1.setId(placeRow1.getLong("id"));
                                place1.setCity(placeRow1.getString("city"));
                                place1.setAddress(placeRow1.getString("address"));

                                System.out.println("Znaleziono miejsce podr????y: " + place1);
                            }
                            break;
                        default:
                            return;
                    }
                    break;
                case 3:
                    System.out.println("Pobieranie podr????y z bazy danych po: \n1 - Id \n2 - Cenie");
                    int travelSelectedOption = scanner.nextInt();
                    switch (travelSelectedOption) {
                        case 1:
                            System.out.println("Podaj id podr????y: ");
                            Travel travel = new Travel();
                            travel.setId(scanner.nextLong());

                            String selectTravelById = "SELECT * FROM Travel WHERE id = " + travel.getId() + ";";
                            Row travelRow = session.execute(selectTravelById).one();
                            if (travelRow != null) {
                                travel.setClientId(travelRow.getLong("clientId"));
                                travel.setPlaceId(travelRow.getLong("placeId"));
                                travel.setNumberOfDays(travelRow.getInt("numberOfDays"));
                                travel.setPrice(travelRow.getFloat("price"));

                                System.out.println("Znaleziono podr????: " + travel);
                            }
                            break;
                        case 2:
                            System.out.println("Podaj cen?? podr????y: ");
                            Travel travel1 = new Travel();
                            travel1.setPrice(scanner.nextFloat());

                            String selectTravelByPrice = "SELECT * FROM Travel WHERE price = " + travel1.getPrice() + " ALLOW FILTERING;";
                            Row travelRow1 = session.execute(selectTravelByPrice).one();
                            if (travelRow1 != null) {
                                travel1.setId(travelRow1.getLong("id"));
                                travel1.setClientId(travelRow1.getLong("clientId"));
                                travel1.setPlaceId(travelRow1.getLong("placeId"));
                                travel1.setNumberOfDays(travelRow1.getInt("numberOfDays"));

                                System.out.println("Znaleziono podr????: " + travel1);
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

    public void dataProcessing() {
        System.out.println("Obliczanie sumy cen wszystkich podr????y");
        String statement = "SELECT * FROM Travel;";
        ResultSet resultSet = session.execute(statement);

        float sum = 0;
        for (Row row : resultSet)
            sum += (row.getFloat("price"));

        System.out.println("Suma cen wszystkich podr????y: " + sum);
    }
}
