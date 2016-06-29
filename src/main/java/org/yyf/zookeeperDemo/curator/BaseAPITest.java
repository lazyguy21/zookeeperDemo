package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by tobi on 16-6-21.
 */
public class BaseAPITest {
    public static final String zookeeperConnectionString = "localhost:2181";
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();


        client.create().forPath("/my/path", "testForCurator".getBytes());

    }
}
