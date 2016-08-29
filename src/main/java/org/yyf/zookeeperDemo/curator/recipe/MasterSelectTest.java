package org.yyf.zookeeperDemo.curator.recipe;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import static org.yyf.zookeeperDemo.curator.BaseConstants.client;

/**
 * Created by tobi on 16-8-22.
 */
public class MasterSelectTest {
    public static void main(String[] args) throws InterruptedException {
        client.start();
        String path = "/master_select/lock";
        LeaderSelector leaderSelector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println(" to  be master !");

            }
        });

        leaderSelector.autoRequeue();
        leaderSelector.start();

        Thread.sleep(Integer.MAX_VALUE);

    }
}
