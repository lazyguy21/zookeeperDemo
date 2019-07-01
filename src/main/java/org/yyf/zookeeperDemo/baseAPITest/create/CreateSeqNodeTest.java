//package org.yyf.zookeeperDemo.baseAPITest.create;
//
//import org.apache.zookeeper.*;
//
//import java.io.IOException;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by tobi on 16-8-17.
// */
//public class CreateSeqNodeTest implements Watcher {
//    private static CountDownLatch countDownLatch = new CountDownLatch(1);
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 6000, new CreateSeqNodeTest());
//        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 6000, new CreateSeqNodeTest());
//        countDownLatch.await();
//
//        try {
////            String s = zooKeeper.create("/createPERSISTENTNodeTest", "haha".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            String s1 = zooKeeper.create("/createEPHEMERALNodeTest", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.CONTAINER);
//////            String s2 = zooKeeper.create("/testC", "haha2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
////            String s3 = zooKeeper.create("/testC", "haha3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////            System.out.println(s3);
////            String s4 = zooKeeper.create("/testC", "haha3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////            System.out.println(s4);
////            String s5 = zooKeeper.create("/testC", "haha3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////            System.out.println(s5);
////            String s4 = zooKeeper.create("/createNodeTest", "haha4".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.CreateMode;
//            TimeUnit.SECONDS.sleep(1000);
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        System.out.println(event);
//        Event.KeeperState state = event.getState();
//        switch (state) {
//            case Unknown:
//                break;
//            case Disconnected:
//                break;
//            case NoSyncConnected:
//                break;
//            case SyncConnected:
//                System.out.println(state);
//                countDownLatch.countDown();
//                break;
//            case AuthFailed:
//                break;
//            case ConnectedReadOnly:
//                break;
//            case SaslAuthenticated:
//                break;
//            case Expired:
//                break;
//        }
//    }
//}
//
// *Created by tobi on 16-8-17.
//         */
//
//public class CreateSeqNodeTest implements Watcher {
//    private static CountDownLatch countDownLatch = new CountDownLatch(1);
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 6000, new CreateSeqNodeTest());
//        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 6000, new CreateSeqNodeTest());
//        countDownLatch.await();
//
//        try {
////            String s = zooKeeper.create("/createPERSISTENTNodeTest", "haha".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            String s1 = zooKeeper.create("/createEPHEMERALNodeTest", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.CONTAINER);
//////            String s2 = zooKeeper.create("/testC", "haha2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
////            String s3 = zooKeeper.create("/testC", "haha3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////            System.out.println(s3);
////            String s4 = zooKeeper.create("/testC", "haha3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////            System.out.println(s4);
////            String s5 = zooKeeper.create("/testC", "haha3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
////            System.out.println(s5);
////            String s4 = zooKeeper.create("/createNodeTest", "haha4".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.CreateMode;
//            TimeUnit.SECONDS.sleep(1000);
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        System.out.println(event);
//        Event.KeeperState state = event.getState();
//        switch (state) {
//            case Unknown:
//                break;
//            case Disconnected:
//                break;
//            case NoSyncConnected:
//                break;
//            case SyncConnected:
//                System.out.println(state);
//                countDownLatch.countDown();
//                break;
//            case AuthFailed:
//                break;
//            case ConnectedReadOnly:
//                break;
//            case SaslAuthenticated:
//                break;
//            case Expired:
//                break;
//        }
//    }
//}
