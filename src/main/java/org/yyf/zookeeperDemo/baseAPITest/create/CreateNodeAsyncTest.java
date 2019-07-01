package org.yyf.zookeeperDemo.baseAPITest.create;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-17.
 */
public class CreateNodeAsyncTest implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 6000, new CreateNodeAsyncTest());
        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 6000, new CreateNodeAsyncTest());
        countDownLatch.await();


        zooKeeper.create("/testNode", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                System.out.println("rc : " + rc);
                System.out.println("path : " + path);
                System.out.println("ctx : " + ctx);
                System.out.println("name : " + name);

            }
        }, "i am a context object ,pass to callBack function");

        TimeUnit.SECONDS.sleep(1);

        zooKeeper.create("/testNode221", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {

                System.out.println("rc : " + rc);
                KeeperException.Code code = KeeperException.Code.get(rc);//最新版处理rc code的api
                System.out.println("Code : " + code);
                System.out.println("path : " + path);
                System.out.println("ctx : " + ctx);
                System.out.println("name : " + name);

            }
        }, "i am a context object ,pass to callBack function");

        TimeUnit.SECONDS.sleep(1000);
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
