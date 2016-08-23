package org.yyf.zookeeperDemo.curator.recipe.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.yyf.zookeeperDemo.curator.BaseConstants;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-23.
 * 可以监控多个层级的child，比PathCache更强大
 */
public class TreeCacheTest {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(BaseConstants.zookeeperConnectionString, retryPolicy);
        client.start();

        String path = "/treeCacheNode";

        TreeCache treeCache = new TreeCache(client, path);
        treeCache.start();

//        ChildData currentData = treeCache.getCurrentData(path+"/child1");
//        System.out.println(currentData);
//        Map<String, ChildData> currentChildren = treeCache.getCurrentChildren("/child1");
//        System.out.println(currentChildren);

        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(event);
                ChildData currentData = treeCache.getCurrentData(path + "/child1");
                System.out.println(currentData);
                Map<String, ChildData> currentChildren = treeCache.getCurrentChildren("/curatorCreateNode");
                System.out.println(currentChildren);
            }
        });

        TimeUnit.HOURS.sleep(1);
    }
}
