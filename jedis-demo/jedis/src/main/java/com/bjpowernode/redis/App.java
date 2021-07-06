package com.bjpowernode.redis;

import com.bjpowernode.redis.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
       /* //1. 创建Jedis对象
        Jedis jedis = new Jedis("192.168.206.128",6379);
        jedis.flushDB();
        jedis.lpush("hello","java","php","python","c++");
        // 关闭资源
        jedis.close();*/
       // 使用Jedis连接池, 返回连接池对象
        /*JedisPool jedisPool = RedisUtils.open("192.168.206.128", 6379);
        // 从连接池对象中获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        String setStr = jedis.set("str1", "hello java");
        System.out.println(setStr);
        RedisUtils.close();*/

        /**
         * 使用连接池 实现对redis的操作
         */
        JedisPool jedisPool = RedisUtils.open("192.168.206.128", 6379);
        // 从连接池中获取jedis对象
        Jedis jedis = jedisPool.getResource();
       /* Map<String, String> map = new HashMap<String,String>();
        map.put("id","1001");
        map.put("name","老罗");
        map.put("age","18");
        map.put("yanzhi","100");

        String hmset = jedis.hmset("student", map);
        System.out.println("hmset:" + hmset);
        List<String> list = jedis.hmget("student", "id", "name", "age", "yanzhi");
        for (String values : list) {
            System.out.println("values:" + values);
        }*/

       jedis.flushDB();
       // 使用事务
        Transaction multi = jedis.multi();
        multi.set("hello","java");
        multi.set("world","python");
        multi.set("str1","php");
        List<Object> exec = multi.exec();
        for (Object o : exec) {
            System.out.println(o);
        }

        RedisUtils.close();


    }
}
