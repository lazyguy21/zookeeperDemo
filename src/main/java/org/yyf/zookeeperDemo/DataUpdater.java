package org.yyf.zookeeperDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by tobi on 16-6-21.
 */
public class DataUpdater implements Watcher,Runnable{
    private static final String hostPort = "localhost:2181";
    private static final String zooDataPath = "/MyConfig";

    ZooKeeper zk;
    public DataUpdater() throws IOException {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }

    @Override
    public void run() {
        while (true) {
            String uuid = UUID.randomUUID().toString();
            byte zoo_data[] = uuid.getBytes();
            try {
                zk.setData(zooDataPath, zoo_data, -1);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000); // Sleep for 5 secs
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DataUpdater dataUpdater = new DataUpdater();
        dataUpdater.run();
    }

}
