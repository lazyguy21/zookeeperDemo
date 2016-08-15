package org.yyf.zookeeperDemo.firstShot;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;

/**
 * Created by tobi on 16-8-15.
 */
public class Creator implements Watcher{
    ZooKeeper zooKeeper;

    public Creator() {
    }
    public void start(){
        try {
            zooKeeper = new ZooKeeper("localhost:2181", 15000, this);
        } catch (IOException e) {
            throw new RuntimeException("create zookeeper client failed!", e);
        }
    }

    public void create(String path,byte[] data){
        try {
            zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setData(String path,byte[] data){
        try {
            zooKeeper.setData(path, data,-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.start();
//        creator.create("/firstShot","something".getBytes());
        creator.setData("/firstShot","firstSet3".getBytes());
    }
}
