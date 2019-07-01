package org.yyf.zookeeperDemo.baseAPITest.masterworker;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by @author yyf on 2019-06-11.
 */
public class Master {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = initZK();
        String path = "/master";
        byte[] data = "master info maybe host ip".getBytes();
        try {
            zooKeeper.create(path, data, null, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static ZooKeeper initZK() {
        try {
            return new ZooKeeper("localhost:2181", 6000, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
