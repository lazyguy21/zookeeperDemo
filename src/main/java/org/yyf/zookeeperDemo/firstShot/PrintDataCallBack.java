package org.yyf.zookeeperDemo.firstShot;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.io.UnsupportedEncodingException;

/**
 * Created by tobi on 16-8-15.
 */
public class PrintDataCallBack implements AsyncCallback.DataCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        try {
            System.out.println(new String(data,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
