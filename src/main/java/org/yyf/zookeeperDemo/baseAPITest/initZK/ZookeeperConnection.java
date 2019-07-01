package org.yyf.zookeeperDemo.baseAPITest.initZK;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-17.
 */
public class ZookeeperConnection implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            //session的超时时间只能取ticktime的2到20倍，（ticktime默认为2000ms），大了或者小了，服务器取端点值。
            ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 50000,new ZookeeperConnection());//这里会立即返回
//            new ZooKeeper()
            System.out.println(zooKeeper.getSessionTimeout());//还没连上，所以sessionTimeOut返回了0，为啥不返回null
            System.out.println(zooKeeper.getState());//这个时候zookeeper连接还处于Connecting状态
            System.out.println(Thread.currentThread().getName());
            countDownLatch.await();//等待连接好后的事件回调
            System.out.println(zooKeeper.getState());//此时zk的状态已经是connected了
            System.out.println(zooKeeper.getSessionTimeout());//设置1000的时候，服务器默认取为4000了。

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        switch (state) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                System.out.println(Thread.currentThread().getName());
                System.out.println(event);
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
