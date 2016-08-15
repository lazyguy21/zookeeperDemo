package org.yyf.zookeeperDemo.firstShot;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-15.
 */
public class Listener implements Watcher {
    public static void main(String[] args) throws InterruptedException {
        new Listener();
        TimeUnit.SECONDS.sleep(100000L);

    }

    ZooKeeper zooKeeper;
    public Listener() {
        try {
             zooKeeper = new ZooKeeper("localhost:2181", 4000,this);
            byte[] data = zooKeeper.getData("/firstShot", this, null);
            System.out.println(new String(data,"utf-8"));
        } catch (IOException e) {
            throw new RuntimeException("create zookeeper client failed !", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("WatchedEvent : "+event);
        Event.EventType type = event.getType();
        switch (type){
            case NodeDataChanged:
                try {
                    byte[] data = zooKeeper.getData("/firstShot", this, null);
                    System.out.println(new String(data,"utf-8"));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

        }

    }
}
