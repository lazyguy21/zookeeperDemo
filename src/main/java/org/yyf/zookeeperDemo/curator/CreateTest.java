package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import static org.yyf.zookeeperDemo.curator.BaseConstants.zookeeperConnectionString;

/**
 * Created by tobi on 16-8-22.
 */
public class CreateTest {
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10 * 1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        curatorFramework.start();

        try {
//            curatorFramework.create().forPath("/curatorCreateNode");
//            curatorFramework.create().forPath("/curatorCreateNod", "testValue".getBytes());
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/curatorCreateNodeEphemeral", "asdfasdf".getBytes());
            String s = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/a/b", "haha".getBytes());
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
