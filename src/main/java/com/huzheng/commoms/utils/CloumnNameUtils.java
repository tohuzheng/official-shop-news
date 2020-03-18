package com.huzheng.commoms.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author 胡正
 * @Date 2020/3/18 12:48
 * @Description
 */
public class CloumnNameUtils {

    /**
     * @author zheng.hu
     * @date 2020/3/18 12:53
     * @description 把Java驼峰转化成数据库下划线
     */
    public static Map toUnder(Map<String,Object> map){
        if (map == null) {
            return null;
        }
        Map<String,Object> newMap = new HashMap<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String newKey = camel2under(key);
            newMap.put(newKey,map.get(key));
        }
        return newMap;
    }

    public static String camel2under(String c)
    {
        String separator = "_";
        c = c.replaceAll("([a-z])([A-Z])", "$1"+separator+"$2").toLowerCase();
        return c;
    }
}
