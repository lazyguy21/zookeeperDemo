package org.yyf.zookeeperDemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-22.
 */
public class DeleteTest {
    public static void main(String[] args) throws InterruptedException {
        String connectionString = BaseConstants.zookeeperConnectionString;
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectionString, exponentialBackoffRetry);
        curatorFramework.start();

        try {
//            curatorFramework.delete().forPath("/a");
//            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/a");
            curatorFramework.delete().guaranteed().forPath("/a");
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
