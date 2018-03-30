package com.zouyyu;

import com.zouyyu.utils.RedisConfigFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author YuZou
 * @date 29/03/2018
 */
public class RedisConnection {

    private JedisPool pool;

    public JedisPool pool(){
        if(pool == null){
            System.out.println(RedisConfigFactory.getRedisURI());
          pool =  new JedisPool(new JedisPoolConfig(), RedisConfigFactory.getRedisURI());
        }
        return  pool;
    }
}
