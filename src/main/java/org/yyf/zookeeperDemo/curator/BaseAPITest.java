package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-6-21.
 */
public class BaseAPITest {
    public static final String zookeeperConnectionString = "localhost:2181";
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        CuratorFrameworkState state = client.getState();
        System.out.println(state);
        client.start();
        System.out.println(state);
        byte[] bytes = client.getData().forPath("/setDataNode");
        System.out.println(new String(bytes));
        System.out.println(state);


//        String path = "/testCurator";
//        String path2 = "/testCuratorNothing";
//        Stat stat = client.checkExists().forPath(path);
//        Stat stat2 = client.checkExists().forPath(path2);
//        if (stat!=null){
//            client.delete().forPath(path);
//        }
//        if (stat2 != null) {
//            client.delete().forPath(path2);
//        }
//        CreateBuilder createBuilder = client.create();
//        createBuilder.forPath(path, "testForCurator".getBytes());
//        createBuilder.forPath(path2);

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
