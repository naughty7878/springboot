package com.test.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestJedis {

    @Test
    public void run1() {
        Jedis jedis = new Jedis("47.106.74.177", 16379);
        jedis.auth("123456");
        jedis.set("sex", "男");
        System.out.println(jedis.get("sex"));
    }

}
