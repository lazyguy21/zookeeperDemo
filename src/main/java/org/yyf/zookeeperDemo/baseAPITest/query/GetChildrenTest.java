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
 * 注意getChildren watch的是子节点的添加或者删除，并不包括子节点的数据变化！！！
 */
public class GetChildrenTest implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat=new Stat();
    private static ZooKeeper zooKeeper;
    private static String path="/getChildrenNode";;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();

//        zooKeeper.create(path, "whatever".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, (rc, path, ctx, name, stat) -> {
//            KeeperException.Code code = KeeperException.Code.get(rc);
//            System.out.println(code);
//            switch (code) {
//                case OK:
//                    GetChildrenTest.stat = stat;
//                    break;
//
//            }
//        }, "ctx object");
        zooKeeper.create(path, "whatever".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,stat);
        System.out.println(stat);

        Thread.sleep(1000*3);
//        getChildren1(zooKeeper);
        getChildren2(zooKeeper);
        Thread.sleep(1000*1000);

    }

    /**
     * 同步查询，不设置watch
     */
    private static void getChildren1(ZooKeeper zooKeeper) throws InterruptedException {
        try {
            List<String> children = zooKeeper.getChildren("/", false);
            System.out.println(children);
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private static void getChildren2(ZooKeeper zooKeeper) {
        ZooKeeper.States state = zooKeeper.getState();
        try {
            System.out.println(state);
            zooKeeper.getChildren(path,new GetChildrenTest(), stat);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        Watcher.Event.EventType type = event.getType();
        System.out.println(event);
        switch (type) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:

                break;
            case NodeChildrenChanged:
                try {
                    List<String> children = zooKeeper.getChildren(path, this, stat);
                    System.out.println(GetChildrenTest.stat);
                    System.out.println("children : "+children);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
        }
    }


}
