package org.yyf.zookeeperDemo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by tobi on 16-8-22.
 */
public class BaseConstants {
    public static final String zookeeperConnectionString = "localhost:2181";
    public static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    public static final CuratorFramework client = CuratorFrameworkFactory.newClient(BaseConstants.zookeeperConnectionString, retryPolicy);
}
