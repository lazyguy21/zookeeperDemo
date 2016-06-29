package org.yyf.zookeeperDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * Created by tobi on 16-6-21.
 */
public class HelloZookeeper {
    public static void main(String[] args) throws IOException {
//        String host = "10.8.12.128:32770";
//        String host = "10.18.19.18:12181";
        String host = "localhost:2181";
        String zPath = "/";

        ZooKeeper zk = new ZooKeeper(host, 15000, null);

        try {
            List<String> children = zk.getChildren(zPath, false);
            System.out.println(children);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
