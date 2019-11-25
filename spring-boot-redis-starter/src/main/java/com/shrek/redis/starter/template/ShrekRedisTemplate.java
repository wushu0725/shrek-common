package com.shrek.redis.starter.template;


import com.shrek.redis.starter.adapter.RedisAdapter;
import com.shrek.redis.starter.core.RedisEnvironment;
import com.shrek.redis.starter.util.RedisBeanUtil;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

/**
 * redis 操作模板类
 *
 * @author niuzhiwei
 */
@Slf4j
public class ShrekRedisTemplate {

    /**
     * 判断redis#key键是否存在
     */
    public boolean exists(String key) {
        return getRedisAdapter().getRedisProcessor().exists(key);
    }

    /**
     * 通过redis#key判断value长度
     */
    public Long getLength(String key) {
        return getRedisAdapter().getRedisProcessor().length(key);
    }

    /**
     * 新增 value为Object类型
     */
    public void set(String key, Object obj) {
        getRedisAdapter().getRedisProcessor().set(key, obj);
    }

    /**
     * 新增 value为String类型
     */
    public void set(String key, String value) {
        getRedisAdapter().getRedisProcessor().set(key, value);
    }

    /**
     * 新增 value为String类型 seconds 有效期（单位：s）--正数
     */
    public void set(String key, String value, int seconds) {
        getRedisAdapter().getRedisProcessor().setex(key, value, seconds);
    }

    /**
     * 新增 value为Object类型 seconds 有效期（单位：s）--正数
     */
    public void set(String key, Object obj, int seconds) {
        getRedisAdapter().getRedisProcessor().setex(key, obj, seconds);
    }

    /**
     * 获取key的值
     */
    public String get(String key) {
        return getRedisAdapter().getRedisProcessor().get(key);
    }


    /**
     * 获取多个key值
     */
    public List<String> get(String... keys) {
        return getRedisAdapter().getRedisProcessor().get(keys);
    }

    /**
     * 正则批量获取key
     */
    public ScanResult<String> scan(int index, String regx) {
        return getRedisAdapter().getRedisProcessor().scan(index, regx);
    }

    /**
     * 正则批量获取key
     */
    public ScanResult<String> scan(String regx) {
        return getRedisAdapter().getRedisProcessor().scan(regx);
    }

    /**
     * 获取key的值 class为需要转换为的对象
     */
    public <T> T get(String key, Class<T> clazz) {
        return getRedisAdapter().getRedisProcessor().get(key, clazz);
    }

    /**
     * 获取key的有效期
     */
    public Long getValid(String key) {
        return getRedisAdapter().getRedisProcessor().getValid(key);
    }

    /**
     * 删除key的有效期
     */
    public Long delValid(String key) {
        return getRedisAdapter().getRedisProcessor().removeValid(key);
    }

    /**
     * 获取Key的生存时间
     */
    public Long ttl(String key) {
        return getRedisAdapter().getRedisProcessor().getTimeOut(key);
    }

    /**
     * 删除key
     */
    public Long del(String key) {
        return getRedisAdapter().getRedisProcessor().delete(key);
    }

    /**
     * key值自增1
     */
    public Long incr(String key) {
        return getRedisAdapter().getRedisProcessor().incr(key);
    }

    /**
     * hash类型存储数据
     */
    public void hset(String key, String field, String value) {
        getRedisAdapter().getRedisProcessor().hset(key, field, value);
    }

    /**
     * hash类型获取数据
     */
    public String hget(String key, String field) {
        return getRedisAdapter().getRedisProcessor().hget(key, field);
    }

    /**
     * hash类型删除数据
     */
    public Long hdel(String key, String field) {
        return getRedisAdapter().getRedisProcessor().hdel(key, field);
    }

    /**
     * hash类型批量添加
     */
    public void hmset(String key, Map<String, String> map) {
        getRedisAdapter().getRedisProcessor().hmset(key, map);
    }

    /**
     * hash类型批量获取
     */
    public List<String> hmget(String key, String... fields) {
        return getRedisAdapter().getRedisProcessor().hmget(key, fields);
    }

    /**
     * hash获取属性个数
     */
    public Long hlen(String key) {
        return getRedisAdapter().getRedisProcessor().hlen(key);
    }

    /**
     * hash获取全部
     */
    public Map<String, String> hgetAll(String key) {
        return getRedisAdapter().getRedisProcessor().hgetAll(key);
    }

    /**
     * 从左边入列表
     */
    public void lpush(String key, String... strings) {
        getRedisAdapter().getRedisProcessor().lpush(key, strings);
    }

    /**
     *  从右边入列表
     */
    public void rpush(String key, String... strings) {
        getRedisAdapter().getRedisProcessor().rpush(key, strings);
    }

    /**
     *  返回列表长度
     */
    public Long llen(String key) {
        return getRedisAdapter().getRedisProcessor().llen(key);
    }

    /**
     * 返回index下标的元素
     */
    public String lindex(String key, long index) {
        return getRedisAdapter().getRedisProcessor().lindex(key, index);
    }

    /**
     * 获取key列表所有元素
     */
    public List<String> lrangeAll(String key) {
        return getRedisAdapter().getRedisProcessor().lrangeAll(key);
    }

    /**
     * 获取列表指定范围的元素
     */
    public List<String> lrange(String key, long start, long end) {
        return getRedisAdapter().getRedisProcessor().lrange(key, start, end);
    }

    /**
     * 弹出列表头部元素
     */
    public String lpop(String key) {
        return getRedisAdapter().getRedisProcessor().lpop(key);
    }

    /**
     * 弹出列表尾部元素
     */
    public String rpop(String key) {
        return getRedisAdapter().getRedisProcessor().rpop(key);
    }


    /**
     * 设置key的过期时间
     */
    public void expire(String key, int seconds) {
        getRedisAdapter().getRedisProcessor().setTimeOut(key, seconds);
    }


    /**
     * 分布式锁-锁定，返回值:true-获取锁成功/false-获取锁失败
     */
    public boolean tryLock(String key, String value, Long expire) {
        return getRedisAdapter().getRedisProcessor().tryLock(key, value, expire);
    }

    /**
     * 分布式锁-解锁，返回值:true-解除锁成功/false-解除锁失败
     */
    public boolean unLock(String key, String value) {
        return getRedisAdapter().getRedisProcessor().unLock(key, value);
    }

    /**
     *分布式等待锁-锁定，返回值:true-获取锁成功/false-获取锁失败
     *   expireSecond:持有锁超时毫秒数，waitSecond:等待锁超时秒数,flag:线程标识调用getThreadFlag()获取
     */
    public boolean tryLockWait(String key, long expireSecond, int waitSecond, String flag) {
        return getRedisAdapter().getRedisProcessor().tryLockWait(key, expireSecond, waitSecond, flag);
    }

    /**
     *分布式等待锁-解锁，返回值:true-获取锁成功/false-获取锁失败-flag:线程标识调用getThreadFlag()获取
     */
    public boolean unlockWait(String key, String flag) {
        return getRedisAdapter().getRedisProcessor().unlockWait(key, flag);
    }

    /**
     *获取线程标识
     */
    public String getThreadFlag() {
        return getRedisAdapter().getRedisProcessor().getThreadFlag();
    }

    private RedisAdapter getRedisAdapter() {

        RedisEnvironment environment = RedisBeanUtil.getBean(RedisEnvironment.class);

        if (!environment.getRedisConnectionFactoryIsInit()) {
            log.error("Please open the redis integration annotation" +
                    " @enableEtcRedis and select redis mode {single, cluster, sentinel}");
            throw new RuntimeException("Please open the redis integration annotation" +
                    " @enableEtcRedis and select redis mode {single, cluster, sentinel}");
        }

        return RedisBeanUtil.getBean(RedisAdapter.class);
    }

}
