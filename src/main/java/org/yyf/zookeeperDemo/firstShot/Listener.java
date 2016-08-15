package org.yyf.zookeeperDemo.firstShot;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-15.
 */
public class Listener implements Watcher {
    public static void main(String[] args) throws InterruptedException {
        Listener listener = new Listener();
        listener.zooKeeper.getData("/firstShot", true, new PrintDataCallBack(), null);

        TimeUnit.SECONDS.sleep(100000L);

    }

    ZooKeeper zooKeeper;

    public Listener() {
        try {
            zooKeeper = new ZooKeeper("localhost:2181", 4000, this);
        } catch (IOException e) {
            throw new RuntimeException("create zookeeper client failed !", e);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("WatchedEvent : " + event);
        Event.EventType type = event.getType();
        switch (type) {
            case NodeDataChanged:
                zooKeeper.getData("/firstShot", true, new PrintDataCallBack(), null);
        }

    }

}
