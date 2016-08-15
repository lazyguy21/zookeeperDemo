package org.yyf.zookeeperDemo.firstShot;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-15.
 */
public class MainContext {
    public static void main(String[] args) throws InterruptedException {
        new Listener();
        TimeUnit.SECONDS.sleep(10000L);
    }
}
