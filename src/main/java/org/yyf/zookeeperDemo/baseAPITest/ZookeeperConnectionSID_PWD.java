package org.yyf.zookeeperDemo.baseAPITest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-17.
 */
public class ZookeeperConnectionSID_PWD implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        try {
            String connectString = "localhost:2181";
            int sessionTimeout = 5000;
            ZookeeperConnectionSID_PWD watcher = new ZookeeperConnectionSID_PWD();
            ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, new ZookeeperConnectionSID_PWD());
            countDownLatch.await();
            //乱填的sid和pwd，server会返回event.state=expired
//            ZooKeeper zooKeeper1 = new ZooKeeper(connectString, sessionTimeout, new ZookeeperConnectionSID_PWD(), 001230L, "fakePWD".getBytes());
//            ZooKeeper.States state = zooKeeper1.getState();
            new ZooKeeper(connectString, sessionTimeout, new ZookeeperConnectionSID_PWD(), zooKeeper.getSessionId(), zooKeeper.getSessionPasswd());
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        System.out.println(event);
        switch (state) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
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
