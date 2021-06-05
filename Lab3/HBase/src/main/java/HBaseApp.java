import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseApp {
    private static final TableName TABLE_NAME = TableName.valueOf("test_table_example");
    private static final byte[] CF_NAME = Bytes.toBytes("test_cf");
    private static final byte[] QUALIFIER = Bytes.toBytes("test_column");
    private static final byte[] ROW_ID = Bytes.toBytes("row01");

    public static void createTable(final Admin admin) throws IOException {
        if(!admin.tableExists(TABLE_NAME)) {
            TableDescriptor desc = TableDescriptorBuilder.newBuilder(TABLE_NAME)
                    .setColumnFamily(ColumnFamilyDescriptorBuilder.of(CF_NAME))
                    .build();
            admin.createTable(desc);
        }
    }

    public static void putRow(final Table table) throws IOException {
        table.put(new Put(ROW_ID).addColumn(CF_NAME, QUALIFIER, Bytes.toBytes("Hello, World!")));
    }

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        System.out.println("v");
        try (Connection connection = ConnectionFactory.createConnection(config); Admin admin = connection.getAdmin()) {
            createTable(admin);
            System.out.println("jest");
            try(Table table = connection.getTable(TABLE_NAME)) {
                putRow(table);
            }
        } catch (Exception e) {
            System.out.println("a");
            e.printStackTrace();
        }
    }
}