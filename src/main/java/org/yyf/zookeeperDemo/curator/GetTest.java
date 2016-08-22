package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import static org.yyf.zookeeperDemo.curator.BaseConstants.zookeeperConnectionString;

/**
 * Created by tobi on 16-8-22.
 */
public class GetTest {
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10 * 1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        curatorFramework.start();
        String path = "/a/b";
        try {
            byte[] bytes = curatorFramework.getData().forPath(path);
            System.out.println(new String(bytes));

            Stat stat = new Stat();
            System.out.println("before stat : "+stat);
            curatorFramework.getData().storingStatIn(stat).forPath(path);
            System.out.println("after stat : "+stat);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
