package org.yyf.zookeeperDemo.baseAPITest.query;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.yyf.zookeeperDemo.baseAPITest.common.CommonZKWatcher;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-19.
 *比较违反直觉的是exist这个api会监听createNode，deleteNode，而且还有NodeDataChanged
 */
public class ExistTest implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat=new Stat();
    private static ZooKeeper zooKeeper;
    private static String path="/existNode";;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();


        zooKeeper.create(path, "whatever".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,stat);
        System.out.println("after create zk ,stat : "+stat);

        zooKeeper.exists(path, new ExistTest());

        Thread.sleep(1000*1000);

    }



    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        System.out.println(event);
        switch (type) {
            case None:
                break;
            case NodeCreated:
                keepExistWatch();
                break;
            case NodeDeleted:
                keepExistWatch();
                break;
            case NodeDataChanged:
                keepExistWatch();
                break;
            case NodeChildrenChanged:

                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
        }
    }

    private void keepExistWatch() {
        try {
            Stat stat = zooKeeper.exists(path, new ExistTest());
            System.out.println(ExistTest.stat);
            System.out.println("the watching stat "+stat);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
