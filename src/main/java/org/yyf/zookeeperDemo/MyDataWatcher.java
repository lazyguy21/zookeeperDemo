package org.yyf.zookeeperDemo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-7-4.
 */
public class MyDataWatcher implements Watcher {
    private final static String zkPath = "/myTestNode";
    private final static String connStr = "localhost:2181";

    private final static String zkNode = "myTestNode";
    private final ZooKeeper zooKeeper;

    public MyDataWatcher() throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(connStr, 30000, this);
        if (zooKeeper.exists(zkPath, this) == null) {
            zooKeeper.create(zkPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        String name = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        System.out.println(name+"  "+id);
        System.out.println("  right now eventType: "+ event.getType());
        if(event.getType()==Event.EventType.NodeDataChanged){
            System.out.println("NodeDataChanged happen !");
            try {
                byte[] data = zooKeeper.getData(zkPath, this,null);
                System.out.println(name + "get the Data :");
                System.out.println(new String(data));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        MyDataWatcher myDataWatcher = new MyDataWatcher();
        String name = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        System.out.println(name+"  "+id);
        while(true){}

    }
}
