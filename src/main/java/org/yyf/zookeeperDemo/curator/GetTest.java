package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

import static org.yyf.zookeeperDemo.curator.BaseConstants.zookeeperConnectionString;

/**
 * Created by tobi on 16-8-22.
 */
public class GetTest {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10 * 1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        curatorFramework.start();
        String path = "/pathChildrenCache";
        try {
            byte[] bytes = curatorFramework.getData().forPath(path);
            System.out.println(new String(bytes));
            curatorFramework.getData().usingWatcher(new CuratorWatcher() {
                @Override
                public void process(WatchedEvent event) throws Exception {
                    System.out.println("event: "+event);
                }
            }).forPath(path);
            Stat stat = new Stat();
            System.out.println("before stat : "+stat);
            curatorFramework.getData().storingStatIn(stat).forPath(path);
            System.out.println("after stat : "+stat);
        } catch (Exception e) {
            e.printStackTrace();
        }


        TimeUnit.HOURS.sleep(1L);

    }
}
