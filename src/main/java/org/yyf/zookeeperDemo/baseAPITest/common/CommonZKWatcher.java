package org.yyf.zookeeperDemo.baseAPITest.common;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-19.
 */
public class CommonZKWatcher implements Watcher {
    private CountDownLatch countDownLatch;
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

    public CommonZKWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
