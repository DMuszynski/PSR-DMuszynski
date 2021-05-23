import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.partition.MigrationListener;
import com.hazelcast.partition.MigrationState;
import com.hazelcast.partition.PartitionService;
import com.hazelcast.partition.ReplicaMigrationEvent;

import java.net.UnknownHostException;

public class ApplicationServer {
    public static void main(String[] args) throws UnknownHostException {
        Config config = HConfig.getConfig();

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

        instance.addDistributedObjectListener(new DistributedObjectListener() {

            @Override
            public void distributedObjectDestroyed(DistributedObjectEvent e) {
                System.out.println(e);
            }

            @Override
            public void distributedObjectCreated(DistributedObjectEvent e) {
                System.out.println(e);
            }
        });

        instance.getCluster().addMembershipListener(new MembershipListener() {

            @Override
            public void memberRemoved(MembershipEvent e) {
                System.out.println(e);
            }

            @Override
            public void memberAdded(MembershipEvent e) {
                System.out.println(e);
            }
        });

        PartitionService partitionService = instance.getPartitionService();
        partitionService.addMigrationListener(new MigrationListener() {

            @Override
            public void replicaMigrationFailed(ReplicaMigrationEvent e) {
                System.out.println(e);
            }

            @Override
            public void replicaMigrationCompleted(ReplicaMigrationEvent e) {
                System.out.println(e);
            }

            @Override
            public void migrationStarted(MigrationState s) {
                System.out.println(s);
            }

            @Override
            public void migrationFinished(MigrationState s) {
                System.out.println(s);
            }
        });

        IMap<Long, Driver> drivers = instance.getMap("drivers");
        IMap<Long, Car> cars = instance.getMap("cars");
        IMap<Long, Passenger> passengers = instance.getMap("passengers");
        IMap<Long, Transport> transports = instance.getMap("transports");

        drivers.addEntryListener(new EntryAddedListener<Long, Driver>() {

            @Override
            public void entryAdded(EntryEvent<Long, Driver> e) {
                System.out.println(e);
            }
        }, true);

        cars.addEntryListener(new EntryAddedListener<Long, Car>() {

            @Override
            public void entryAdded(EntryEvent<Long, Car> e) {
                System.out.println(e);
            }
        }, true);

        passengers.addEntryListener(new EntryAddedListener<Long, Passenger>() {

            @Override
            public void entryAdded(EntryEvent<Long, Passenger> e) {
                System.out.println(e);
            }
        }, true);

        transports.addEntryListener(new EntryAddedListener<Long, Transport>() {

            @Override
            public void entryAdded(EntryEvent<Long, Transport> e) {
                System.out.println(e);
            }
        }, true);
    }
}
