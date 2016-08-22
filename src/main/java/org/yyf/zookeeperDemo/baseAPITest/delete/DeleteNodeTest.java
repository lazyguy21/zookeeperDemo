package org.yyf.zookeeperDemo.baseAPITest.delete;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.yyf.zookeeperDemo.baseAPITest.common.CommonZKWatcher;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-17.
 */
public class DeleteNodeTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();
        Stat stat = new Stat();
        String path = "/versionTest";
        zooKeeper.getData(path,false,stat);
        int version = stat.getVersion();
        System.out.println(version);
        zooKeeper.delete(path,12);//dataVersion从0开始，错误丢出KeeperException$BadVersionException
//        zooKeeper.delete("/firstShot");


    }
}
