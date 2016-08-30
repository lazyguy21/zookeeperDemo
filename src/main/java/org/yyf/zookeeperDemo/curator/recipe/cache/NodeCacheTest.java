package org.yyf.zookeeperDemo.curator.recipe.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.yyf.zookeeperDemo.curator.BaseConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-22. 监听node节点的创建，删除，数据变更
 */
public class NodeCacheTest {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(BaseConstants.zookeeperConnectionString, retryPolicy);
        client.start();
        CuratorFrameworkState state = client.getState();


        NodeCache nodeCache = new NodeCache(client, "/nodeCache", false);
        try {
            nodeCache.start(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        while(true){
//            ChildData currentData1 = nodeCache.getCurrentData();
//            TimeUnit.SECONDS.sleep(2);
//            System.out.println(currentData1);
////            System.out.println(currentData1.getData());
//        }

        nodeCache.getListenable().addListener(() -> {
            System.out.println("hah");
            ChildData currentData = nodeCache.getCurrentData();
            System.out.println(currentData);
        });


        CloseableUtils.closeQuietly(nodeCache);
    }
}
