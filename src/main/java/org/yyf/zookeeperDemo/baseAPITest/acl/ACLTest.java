package org.yyf.zookeeperDemo.baseAPITest.acl;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.yyf.zookeeperDemo.baseAPITest.common.CommonZKWatcher;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-17.
 */
public class ACLTest implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();

//        zooKeeper.addAuthInfo("digest",);
//        zooKeeper.delete("/firstShot");


    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
        Event.KeeperState state = event.getState();
        switch (state) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                System.out.println(state);
                countDownLatch.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
        }
    }
}
