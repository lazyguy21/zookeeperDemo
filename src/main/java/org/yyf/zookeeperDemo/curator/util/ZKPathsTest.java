package org.yyf.zookeeperDemo.curator.util;

import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by tobi on 2017/3/24.
 */
public class ZKPathsTest {
    private static String CONNET_STRING = "localhost:2181";
    private static ZooKeeper zooKeeper;
    @org.junit.Before
    public  void doBeforeClass() throws IOException {
        zooKeeper = new ZooKeeper(CONNET_STRING, 1000, null);
    }

    /**
     * 按传入的path值递归创建节点
     */
    @Test
    public void testmkdirs(){
        try {
            ZKPaths.mkdirs(zooKeeper, "/test/curator/ZKPaths");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回path所代表的最后一个节点名字
     * 这就是一个单纯的字符串工具方法，没有连接zk
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String path = "/test/curator/ZKPaths";
        String nodeFromPath = ZKPaths.getNodeFromPath(path);
        Assert.assertTrue("ZKPaths".equals(nodeFromPath));

    }

    /**
     * 这方法就是zookeeper.getChildren(path, false);
     * 然后把查到的节点安字符串名字排序
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        String path = "/";
        List<String> sortedChildren = ZKPaths.getSortedChildren(zooKeeper, path);
        System.out.println(sortedChildren);

    }

    /**
     * path拼接工具，好处是你可以不care，节点直接的"/"写了没写都不会影响
     */
    @Test
    public void test4(){
        String child = ZKPaths.makePath("/parent/test/", "/child","child2");
        String child2 = ZKPaths.makePath("/parent/test/", "child","child2");
        System.out.println(child);
        System.out.println(child2);
    }

    /**
     * 同上……
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        String s = ZKPaths.fixForNamespace("/com/somecompany/someapp/", "/somePath",false);
        System.out.println(s);
    }
}
