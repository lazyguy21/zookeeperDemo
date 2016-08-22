package org.yyf.zookeeperDemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.yyf.zookeeperDemo.simpleWatchDemo.Executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tobi on 16-8-22.
 */
public class AsyncTest {
    public static void main(String[] args) throws InterruptedException {
        String connectionString = BaseConstants.zookeeperConnectionString;
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectionString, exponentialBackoffRetry);
        curatorFramework.start();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            curatorFramework.create().creatingParentsIfNeeded().inBackground((client, event) -> {
                System.out.println("event : " + event);
                System.out.println("current thread : "+Thread.currentThread().getName());

                countDownLatch.countDown();
//            })//不传入，用的是跟zk绑定的event线程
        },executor)//这里传入线程池后，执行任务的线程变为了线程池中的线程
                    .forPath("/curatorAsync");
        } catch (Exception e) {
            e.printStackTrace();
        }

        countDownLatch.await();
        executor.shutdown();
    }
}
