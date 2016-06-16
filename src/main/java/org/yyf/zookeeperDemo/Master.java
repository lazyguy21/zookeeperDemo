package org.yyf.zookeeperDemo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Random;

/**
 * Created by lazyguy on 2016-4-28.
 */
public class Master implements Watcher {
    ZooKeeper zk;
    String connectString;

    Master(String connectString) {
        this.connectString = connectString;
    }

    void startZK() {
        try {
            zk = new ZooKeeper(connectString, 15000, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(WatchedEvent e) {
        System.out.println(e);
    }

    void stopZK() throws Exception {
        zk.close();
    }
    String serverId = Integer.toHexString(new Random().nextInt());
    void runForMaster() throws KeeperException, InterruptedException {
        zk.create("/master",
                serverId.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
    }
    public static void main(String args[]) throws Exception {
        Master m = new Master("192.168.99.100:32773");
        m.startZK();
        // wait for a bit
        Thread.sleep(20000);
        m.runForMaster();
        Thread.sleep(1000000);
        m.stopZK();
    }

}
