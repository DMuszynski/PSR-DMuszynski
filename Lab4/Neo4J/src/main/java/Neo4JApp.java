import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Value;
import org.neo4j.driver.util.Pair;

import java.util.*;

public class Neo4JApp {
    private static final Scanner scanner = new Scanner(System.in);

    static private final String SERVER_URI = "bolt://localhost:7687";
    static private final String SERVER_USERNAME = "neo4j";
    static private final String SERVER_PASSWORD = "neo4jpassword";

    public static Result saveOperation(Transaction transaction) {
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

                String saveOffenderCommand = "CREATE (:Offender {id:$id, firstName:$firstName, lastName:$lastName, salary:$salary })";
                Map<String, Object> saveOffenderParameters = new HashMap<>();
                saveOffenderParameters.put("id", offender.getId());
                saveOffenderParameters.put("firstName", offender.getFirstName());
                saveOffenderParameters.put("lastName", offender.getLastName());
                saveOffenderParameters.put("salary", offender.getSalary());

                System.out.println("Zapisano policjanta: " + offender + "\n");
                return transaction.run(saveOffenderCommand, saveOffenderParameters);
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

                String saveVictimCommand = "CREATE (:Victim {id:$id, firstName:$firstName, lastName:$lastName, address:$address })";
                Map<String, Object> saveVictimParameters = new HashMap<>();
                saveVictimParameters.put("id", victim.getId());
                saveVictimParameters.put("firstName", victim.getFirstName());
                saveVictimParameters.put("lastName", victim.getLastName());
                saveVictimParameters.put("address", victim.getAddress());

                System.out.println("Zapisano poszkodowanego: " + victim + "\n");
                return transaction.run(saveVictimCommand, saveVictimParameters);
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

                String saveCrimeCommand = "CREATE (:Crime {id:$id , idOffender:$idOffender , idVictim:$idVictim, crimeType:$crimeType, crimeDate:$crimeDate })";
                Map<String, Object> saveCrimeParameters = new HashMap<>();
                saveCrimeParameters.put("id", crime.getId());
                saveCrimeParameters.put("idOffender", crime.getIdOffender());
                saveCrimeParameters.put("idVictim", crime.getIdVictim());
                saveCrimeParameters.put("crimeType", crime.getCrimeType());
                saveCrimeParameters.put("crimeDate", crime.getCrimeDate());

                System.out.println("Zapisano przestępstwo: " + crime + "\n");
                return transaction.run(saveCrimeCommand, saveCrimeParameters);
            default: return null;
        }
    }

    public static Result updateOperation(Transaction transaction) {
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

                String updateOffenderCommand = "MATCH (o:Offender {id: '"+  offender.getId()+"' } ) " +
                        "SET o.firstName = '"+offender.getFirstName()+"', o.lastName = '"+offender.getLastName()+"', o.salary = '"+offender.getSalary()+ "' ";
                System.out.println("Zaktualizowano policjanta: " + offender + "\n");
                return transaction.run(updateOffenderCommand);
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

                String updateVictimCommand = "MATCH (v:Victim {id: '"+  victim.getId()+"' } ) " +
                        "SET v.firstName = '"+victim.getFirstName()+"', v.lastName = '"+victim.getLastName()+"', v.address = '"+victim.getAddress()+ "' ";
                System.out.println("Zaktualizowano poszkodowanego: " + victim + "\n");
                return transaction.run(updateVictimCommand);
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

                String updateCrimeCommand = "MATCH (c:Crime {id: '"+  crime.getId()+"' } ) " +
                    "SET c.idOffender = '"+crime.getIdOffender()+"', c.idVictim = '"+crime.getIdVictim() +
                    "', c.crimeType = '"+crime.getCrimeType()+ "', c.crimeDate = '"+crime.getCrimeDate()+"'";
                System.out.println("Zaktualizowano przestępstwo: " + crime + "\n");
                return transaction.run(updateCrimeCommand);
            default: return null;
        }
    }

    public static Result deleteOperation(Transaction transaction) {
        System.out.println("Co chciałbyś usunąć z bazy danych ?");
        System.out.print("1 - Policjanta \n2 - Poszkodowanego \n3 - Przestępstwo \n" +
                "default - zakończenie operacji usuwania z bazy danych\n");
        System.out.println("Proszę wybrać pożądaną opcje: ");

        int selectedOption = scanner.nextInt();
        switch (selectedOption) {
            case 1:
                System.out.println("Podaj id policjanta do usunięcia: ");
                String offenderId = scanner.next();
                String deleteOffenderCommand = "MATCH (o:Offender {id : '"+offenderId+"' }) DETACH DELETE o";
                System.out.println("Usunięto policjanta o id: " + offenderId + "\n");
                return transaction.run(deleteOffenderCommand);
            case 2:
                System.out.println("Podaj id poszkodowanego do usunięcia: ");
                String victimId = scanner.next();
                String deleteVictimCommand = "MATCH (v:Victim {id : '"+victimId+"' }) DETACH DELETE v";
                System.out.println("Usunięto poszkodowanego o id: " + victimId + "\n");
                return transaction.run(deleteVictimCommand);
            case 3:
                System.out.println("Podaj id przestępstwa do usunięcia: ");
                String crimeId = scanner.next();
                String deleteCrimeCommand = "MATCH (c:Crime {id : '"+crimeId+"' }) DETACH DELETE c";
                System.out.println("Usunięto przestępstwo o id: " + crimeId + "\n");
                return transaction.run(deleteCrimeCommand);
            default: return null;
        }
    }

    public static Result getOperation(Transaction transaction) {
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
                        String findOffenderByIdCommand = "MATCH (o:Offender {id : '"+scanner.next()+"' } ) RETURN o";
                        Result findOffenderByIdResult = transaction.run(findOffenderByIdCommand);
                        Optional<Record> findOffenderByIdRecord = findOffenderByIdResult.stream().findFirst();
                        if (findOffenderByIdRecord.isPresent()){
                            List<Pair<String, Value>> fields = findOffenderByIdRecord.get().fields();
                            for (Pair<String, Value> field : fields)
                                System.out.println("Znaleziono policjanta: Offender: " + field.value().asNode().asMap());

                            return findOffenderByIdResult;
                        }
                        break;
                    case 2:
                        System.out.println("Podaj imię policjanata: ");
                        String findOffenderByNameCommand = "MATCH (o:Offender {firstName : '"+scanner.next()+"' } ) RETURN o";
                        Result findOffenderByNameResult = transaction.run(findOffenderByNameCommand);
                        Optional<Record> findOffenderByNameRecord = findOffenderByNameResult.stream().findFirst();
                        if (findOffenderByNameRecord.isPresent()){
                            List<Pair<String, Value>> fields = findOffenderByNameRecord.get().fields();
                            for (Pair<String, Value> field : fields)
                                System.out.println("Znaleziono policjanta: Offender: " + field.value().asNode().asMap());

                            return findOffenderByNameResult;
                        }
                        break;
                    default:
                        return null;
                }
                break;
            case 2:
                System.out.println("Pobieranie poszkodowanego z bazy danych po: \n1 - Id \n2 - Nazwisku");
                int placeSelectedOption = scanner.nextInt();
                switch (placeSelectedOption) {
                    case 1:
                        System.out.println("Podaj id poszkodowanego: ");
                        String findVictimByIdCommand = "MATCH (v:Victim {id : '"+scanner.next()+"' } ) RETURN v";
                        Result findVictimByIdResult = transaction.run(findVictimByIdCommand);
                        Optional<Record> findVictimByIdRecord = findVictimByIdResult.stream().findFirst();
                        if (findVictimByIdRecord.isPresent()){
                            List<Pair<String, Value>> fields = findVictimByIdRecord.get().fields();
                            for (Pair<String, Value> field : fields)
                                System.out.println("Znaleziono poszkodowanego: Victim: " + field.value().asNode().asMap());

                            return findVictimByIdResult;
                        }
                        break;
                    case 2:
                        System.out.println("Podaj nazwisko poszkodowanego: ");
                        String findVictimByNameCommand = "MATCH (v:Victim {lastName : '"+scanner.next()+"' } ) RETURN v";
                        Result findVictimByNameResult = transaction.run(findVictimByNameCommand);
                        Optional<Record> findVictimByNameRecord = findVictimByNameResult.stream().findFirst();
                        if (findVictimByNameRecord.isPresent()){
                            List<Pair<String, Value>> fields = findVictimByNameRecord.get().fields();
                            for (Pair<String, Value> field : fields)
                                System.out.println("Znaleziono poszkodowanego: Victim: " + field.value().asNode().asMap());

                            return findVictimByNameResult;
                        }
                        break;
                    default:
                        return null;
                }
                break;
            case 3:
                System.out.println("Pobieranie przestępstwa z bazy danych po: \n1 - Id \n2 - Typie przestępstwa");
                int travelSelectedOption = scanner.nextInt();
                switch (travelSelectedOption) {
                    case 1:
                        System.out.println("Podaj id przestępstwa: ");
                        String findCrimeByIdCommand = "MATCH (c:Crime {id : '"+scanner.next()+"' } ) RETURN c";
                        Result findCrimeByIdResult = transaction.run(findCrimeByIdCommand);
                        Optional<Record> findCrimeByIdRecord = findCrimeByIdResult.stream().findFirst();
                        if (findCrimeByIdRecord.isPresent()){
                            List<Pair<String, Value>> fields = findCrimeByIdRecord.get().fields();
                            for (Pair<String, Value> field : fields)
                                System.out.println("Znaleziono przestępstwo: Crime: " + field.value().asNode().asMap());

                            return findCrimeByIdResult;
                        }
                        break;
                    case 2:
                        System.out.println("Podaj typ przestępstwa: ");
                        String findCrimeByTypeCommand = "MATCH (c:Crime {crimeType : '"+scanner.next()+"' } ) RETURN c";
                        Result findCrimeByTypeResult = transaction.run(findCrimeByTypeCommand);
                        Optional<Record> findCrimeByTypeRecord = findCrimeByTypeResult.stream().findFirst();
                        if (findCrimeByTypeRecord.isPresent()){
                            List<Pair<String, Value>> fields = findCrimeByTypeRecord.get().fields();
                            for (Pair<String, Value> field : fields)
                                System.out.println("Znaleziono przestępstwo: Crime: " + field.value().asNode().asMap());

                            return findCrimeByTypeResult;
                        }
                        break;
                    default:
                        return null;
                }
                break;
            default: return null;
        }
        return null;
    }

    public static Result dataProcessing(Transaction transaction) {
        System.out.println("Obliczanie sumy wypłat wszystkich policjantów");
        String findAllOffendersCommand = "MATCH (o:Offender) RETURN o";
        Result result = transaction.run(findAllOffendersCommand);
        double sum = 0;
        while (result.hasNext()) {
            Record record = result.next();
            List<Pair<String, Value>> fields = record.fields();
            for (Pair<String, Value> field : fields)
                sum += (double) field.value().asNode().asMap().get("salary");
        }
        System.out.println("Suma wszystkich wypłat policjantów: " + sum);
        return result;
    }

    public static Result deleteEverything(Transaction transaction) {
        String command = "MATCH (n) DETACH DELETE n";
        System.out.println("Executing: " + command);
        return transaction.run(command);
    }

    public static void main (String[]args) {
        try (Driver driver = GraphDatabase.driver(SERVER_URI, AuthTokens.basic(SERVER_USERNAME, SERVER_PASSWORD));
             Session session = driver.session()) {
            session.writeTransaction(Neo4JApp::deleteEverything);
            System.out.println("Połączono z bazą Neo4J !");
            while (true) {
                System.out.println("Witamy w systemie policji!");
                System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                        + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
                System.out.println("Proszę wybrać pożądaną operacje: ");
                int selectedOption = scanner.nextInt();
                switch (selectedOption) {
                    case 1:
                        session.writeTransaction(Neo4JApp::saveOperation);
                        break;
                    case 2:
                        session.writeTransaction(Neo4JApp::updateOperation);
                        break;
                    case 3:
                        session.writeTransaction(Neo4JApp::deleteOperation);
                        break;
                    case 4:
                        session.writeTransaction(Neo4JApp::getOperation);
                        break;
                    case 5:
                        session.writeTransaction(Neo4JApp::dataProcessing);
                        break;
                    default:
                        session.close();
                        return;
                }
            }
        }
    }
}
