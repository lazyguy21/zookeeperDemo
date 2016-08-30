package org.yyf.zookeeperDemo.curator.recipe.cache;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-8-30.
 */
public class ConfigReaderTest {
    public static void main(String[] args) throws InterruptedException {
        String fullPath = "/curatorCreateNod";
        ConfigReader configReader = new ConfigReader("localhost:2181", fullPath);
        configReader.init();
//        configReader.read();
        configReader.readWithCache();

        for (;;) {
            Object o = configReader.get("curatorCreateNod");
            System.out.println(o);
            TimeUnit.SECONDS.sleep(2);
        }

//        TimeUnit.SECONDS.sleep(1000);
    }
}
