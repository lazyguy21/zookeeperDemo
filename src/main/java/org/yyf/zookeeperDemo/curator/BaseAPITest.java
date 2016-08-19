package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Created by tobi on 16-6-21.
 */
public class BaseAPITest {
    public static final String zookeeperConnectionString = "localhost:2181";
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
//        CuratorFrameworkFactory.newClient()
        client.start();


        String path = "/testCurator";
        String path2 = "/testCuratorNothing";
        Stat stat = client.checkExists().forPath(path);
        Stat stat2 = client.checkExists().forPath(path2);
        if (stat!=null){
            client.delete().forPath(path);
        }
        if (stat2 != null) {
            client.delete().forPath(path2);
        }
        CreateBuilder createBuilder = client.create();
        createBuilder.forPath(path, "testForCurator".getBytes());
        createBuilder.forPath(path2);

    }
}
