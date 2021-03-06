package com.common.utils

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.Collections;

/**
 * Redis分布式锁
 *
 * @author huangzy
 * @version 2.0
 * @since 2.0
 * <p>
 * created on 2018/7/3 15:49
 */
public class RedisDistributedLock {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param redisTemplate
     * @param lockKey
     * @param requestId
     * @param expireTime 过期时间 单位是秒
     * @param waitTime 等待时间 单位是秒
     * @return
     */
    public static boolean tryGetLock(RedisTemplate redisTemplate, String lockKey, String requestId, int expireTime,int waitTime) {
        boolean lock = false;
        long oldCurrentTimeMillis = System.currentTimeMillis() + waitTime*1000;
        while (!lock
                && System.currentTimeMillis()<oldCurrentTimeMillis) {

            lock = tryGetLock(redisTemplate,lockKey,requestId,expireTime);

            //获取不到锁，休眠1秒。
            if (!lock) {
                try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {}
            }

        }
        return lock;
    }

    /**
     * 尝试获取分布式锁
     * @param redisTemplate Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间 单位是秒
     * @return 是否获取成功
     */
    public static boolean tryGetLock(RedisTemplate redisTemplate, String lockKey, String requestId, int expireTime) {
        String result = (String)redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            }
        });

        return LOCK_SUCCESS.equals(result);
    }


    /**
     * 释放分布式锁
     * @param redisTemplate Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean unlock(RedisTemplate redisTemplate, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Long result = (Long) redisTemplate.execute((RedisCallback)connection->{
            Object nativeConnection = connection.getNativeConnection();
               // 集群模式
               if (nativeConnection instanceof JedisCluster) {
                   return (Long) ((JedisCluster) nativeConnection).eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
               }

               // 单机模式
               else if (nativeConnection instanceof Jedis) {
                   return (Long) ((Jedis) nativeConnection).eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
               }
               return 0L;
        });

        return RELEASE_SUCCESS.equals(result);
    }

}
