package org.yyf.zookeeperDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tobi on 16-6-30.
 */
public class s {
    public static void main(String[] args) {
        String url = "http://www.tuicool.com/articles/QfEri2";
        Pattern p = Pattern.compile("/[^/.]+");
        Matcher matcher = p.matcher(url);
        int i = matcher.groupCount();
        matcher.group();
    }
}
