package org.yyf.zookeeperDemo.curator.recipe.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tobi on 16-8-29.
 */
public class ConfigReader extends ConcurrentHashMap {

    private static final int CFG_CACHE_THREAD_NUM = 2;
    private static final int MAX_RETRIES = 3;
    private static final int BASE_SLEEP_TIMEMS = 5000;
    private static final int CONNECTION_TIMEOUT = 5000;
    public static final String NULL_VALUE_IN_ZK = "null";
    public static final String PATH_DELIMITER = "/";
    //zookeeper连接字符串
    private final String connectString;
    //操作节点
    public String cfgRootNodeName = "/test";
    //path
    public String path;

    public String location;
    public boolean needKeepAlive;
    protected CuratorFramework client;
    protected PathChildrenCache cache;

    private int cfgCacheThreadNum = 2;

    private int maxRetries = 3;
    private int baseSleepTimes = 5000;
    private int connectionTimeout = 5000;
    private Map container = new HashMap<>();
    private String fullPath;

    private CuratorFramework curatorFramework;

    public ConfigReader(String connectString, String fullPath) {
        this.fullPath = fullPath;
        this.connectString = connectString;
    }

    public void init() {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(baseSleepTimes, maxRetries);
        curatorFramework = CuratorFrameworkFactory.newClient(connectString, exponentialBackoffRetry);
        curatorFramework.start();
    }

    public void read() {
        try {
            byte[] bytes = curatorFramework.getData().forPath(fullPath);
            String result = new String(bytes);
            String path = getKeyFromPath(fullPath);
            this.put(path, result);
        } catch (Exception e) {
            throw new RuntimeException("error occurs in reading value from cfgRootNodeName", e);
        }
    }
    public void readWithCache() {
        read();
        NodeCache nodeCache = new NodeCache(curatorFramework, fullPath);
        nodeCache.getListenable().addListener(() -> {
            ChildData currentData = nodeCache.getCurrentData();
            if(currentData!=null){
                byte[] data = currentData.getData();
                this.put(getKeyFromPath(fullPath), new String(data));
            }
        });
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getKeyFromPath(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

}
