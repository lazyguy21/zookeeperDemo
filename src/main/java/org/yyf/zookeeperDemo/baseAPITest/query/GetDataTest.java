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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-19.
 * 注意getChildren watch的是子节点的添加或者删除，并不包括子节点的数据变化！！！
 */
public class GetDataTest implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat=new Stat();
    private static ZooKeeper zooKeeper;
    private static String path="/getDataNode";;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();

        zooKeeper.create(path, "whatever".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,stat);
        System.out.println("init stat : "+stat);

        Thread.sleep(1000*3);
        zooKeeper.getData(path,new GetDataTest(),stat);
        Thread.sleep(1000*1000);

    }



    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        System.out.println("event : "+event);
        switch (type) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:
                try {
                    byte[] data = zooKeeper.getData(path, this, stat);
                    String s = new String(data, "utf-8");
                    System.out.println("after getData stat : "+GetDataTest.stat);
                    System.out.println("result :"+s);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case NodeChildrenChanged:

                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
        }
    }


}
