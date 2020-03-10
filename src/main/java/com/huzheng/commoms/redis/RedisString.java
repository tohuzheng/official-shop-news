package com.huzheng.commoms.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @Author 胡正
 * @Date 2020/3/10 18:08
 * @Description redis的String类型操作类
 */
public class RedisString {

    private static RedisString redisString = new RedisString();

    /**
     * @author zheng.hu
     * @date 2020/3/10 19:09
     * @description 获取实例对象
     */
    public static RedisString getInstans(){
        return redisString;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 19:05
     * @description 获取redis连接
     */
    public Jedis getConn(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.connect();
        return jedis;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 19:05
     * @description 根据key获取redis的值
     */
    public String getValueByKey(String key){
        Jedis jedis = redisString.getConn();
        String value = jedis.get(key);
        jedis.disconnect();
        return value;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 19:14
     * @description 单个数据插入，当时间为整数则设置过期时间小于0则不设置
     */
    public void setData(String key, String value, int seconds){
        Jedis jedis = redisString.getConn();

        if (seconds>0){
            jedis.setex(key, seconds, value);
        }else {
            jedis.set(key, value);
        }

        jedis.disconnect();
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 19:14
     * @description 批量插入，不设置过期时间
     */
    public void setData(Map<String,String> datas){
        Jedis jedis = redisString.getConn();
        for (String key : datas.keySet()) {
            jedis.set(key, datas.get(key));
        }
        jedis.disconnect();
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 19:21
     * @description 删除key
     */
    public void delete(String key){
        Jedis jedis = redisString.getConn();
        jedis.del(key);
        jedis.disconnect();
    }
}
