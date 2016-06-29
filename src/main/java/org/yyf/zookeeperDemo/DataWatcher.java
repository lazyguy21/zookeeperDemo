package org.yyf.zookeeperDemo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by tobi on 16-6-21.
 */
public class DataWatcher implements Watcher, Runnable {
    private static final String hostPort = "localhost:2181";
    private static final String zooDataPath = "/MyConfig";
    byte zooData[] = null;

    ZooKeeper zooKeeper;

    public DataWatcher() {
        try {
            zooKeeper = new ZooKeeper(hostPort, 2000, this);
            if (zooKeeper.exists(zooDataPath, this) == null) {
                zooKeeper.create(zooDataPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        try {
            byte[] data = zooKeeper.getData(zooDataPath, this, null);
            String s = new String(data);
            System.out.println("the value of /MyConfig is " + s);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.printf("Event Received: %s\n", event.toString());
        //We will process only events of type NodeDataChanged
        if (event.getType() == Event.EventType.NodeDataChanged) {
            printData();

        }
    }

    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
        DataWatcher dataWatcher = new DataWatcher();
        dataWatcher.printData();
        dataWatcher.run();
    }
}
