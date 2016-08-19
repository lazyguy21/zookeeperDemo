package org.yyf.zookeeperDemo.baseAPITest.updateData;

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
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-19.
 * 注意getChildren watch的是子节点的添加或者删除，并不包括子节点的数据变化！！！
 */
public class SetDataTest implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat=new Stat();
    private static ZooKeeper zooKeeper;
    private static String path="/setDataNode";;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();

        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, stat);
        System.out.println("after init ,stat ： "+stat);

        Stat stat = zooKeeper.setData(path, "first".getBytes(), -1);
        System.out.println("after the  setData : "+stat);

        zooKeeper.setData(path, "secondValue".getBytes(), stat.getVersion());
        System.out.println("after the  setData : "+stat);

        zooKeeper.setData(path, "third".getBytes(), -1);
        System.out.println("after the  setData : "+stat);


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
                    System.out.println("after getData stat : "+ SetDataTest.stat);
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
