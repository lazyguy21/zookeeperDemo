package org.yyf.zookeeperDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lazyguy on 2016-4-28.
 */
public class Test {
    private static final String host = "localhost";

    public static void main(String[] args) {
        System.out.println("主线程"+Thread.currentThread().getName());
        long id = Thread.currentThread().getId();
        System.out.println(id);
        try {
            ZooKeeper zk = new ZooKeeper(host, 2000,new TestWatcher());
            zk.exists("/test", true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TestWatcher implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        System.out.println("执行watcher的线程"+Thread.currentThread().getName());
        long id = Thread.currentThread().getId();
        System.out.println(id);
        System.out.println(event);
    }
}