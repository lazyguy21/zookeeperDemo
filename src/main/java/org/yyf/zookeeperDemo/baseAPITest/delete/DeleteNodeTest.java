package org.yyf.zookeeperDemo.baseAPITest.delete;

import org.apache.zookeeper.ZooKeeper;
import org.yyf.zookeeperDemo.baseAPITest.common.CommonZKWatcher;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-8-17.
 */
public class DeleteNodeTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 6000, new CommonZKWatcher(countDownLatch));
        countDownLatch.await();

//        zooKeeper.delete("/firstShot");


    }
}
