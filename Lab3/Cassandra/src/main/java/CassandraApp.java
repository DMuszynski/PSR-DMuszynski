import com.datastax.oss.driver.api.core.CqlSession;

import java.util.Scanner;

public class CassandraApp {
    public static void main(String[] args) {
        CqlSession session = CqlSession.builder().build();
        KeyspaceManager keyspaceManager = new KeyspaceManager(session, "TravelAgency");
        Scanner scanner = new Scanner(System.in);
        keyspaceManager.dropKeyspace();
        keyspaceManager.selectKeyspaces();
        keyspaceManager.createKeyspace();
        keyspaceManager.useKeyspace();

        TravelAgencyManager travelAgencyManager = new TravelAgencyManager(session);
        travelAgencyManager.createTable();
        System.out.println("Połączono z bazą Cassandra !");
        while (true) {
            System.out.println("Witamy w systemie biura podróży!");
            System.out.print("1 - Zapis do bazy danych \n2 - Aktualizacja bazy danych \n3 - Usunięcie z bazy danych \n"
                    + "4 - Pobieranie z bazy danych \n5 - Przetwarzanie danych w bazie danych \ndefault - Koniec programu \n");
            System.out.println("Proszę wybrać pożądaną operacje: ");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    travelAgencyManager.saveOperation();
                    break;
                case 2:
                    travelAgencyManager.updateOperation();
                    break;
                case 3:
                    travelAgencyManager.deleteOperation();
                    break;
                case 4:
                    travelAgencyManager.getOperation();
                    break;
                case 5:
                    travelAgencyManager.dataProcessing();
                    break;
                default:
                    session.close();
                    return;
            }
        }
    }
}
