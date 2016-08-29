package org.yyf.zookeeperDemo.curator.recipe.cache;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.yyf.zookeeperDemo.curator.BaseConstants;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-23.
 */
public class PathCacheTest {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(BaseConstants.zookeeperConnectionString, retryPolicy);
        client.start();

        String path = "/pathChildrenCache";
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println(event);
            }
        });
        pathChildrenCache.start();

        try {
            TimeUnit.HOURS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
