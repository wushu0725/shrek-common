package com.shrek.redis.starter.handler;



import com.shrek.redis.starter.base.AbstractRedisProcessor;
import com.shrek.redis.starter.util.RedisBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * redis单点处理类
 *
 * @author niuzhiwei (niuzhiwei@sinoiov.com)
 */
@Slf4j
public class RedisSingleProcessor extends AbstractRedisProcessor {

    private static Jedis jedis;

    private Jedis getSingle() {
        if (jedis == null) {
            JedisConnectionFactory jedisConnectionFactory = getConnectionFactory();
            jedis = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
            return jedis;
        }
        return jedis;
    }

    @Override
    public boolean exists(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? false : jedis.exists(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return false;
    }

    @Override
    public Long length(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.strlen(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void set(String key, Object obj) {
        Jedis jedis;
        try {
            if (key != null && obj != null) {
                jedis = getSingle();
                jedis.set(key.getBytes(StandardCharsets.UTF_8), serialize(obj));
            } else {
                log.error("easyRedis Single params error,method:SET key:{}  value:{} ", key, obj);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis;
        try {
            if (key != null && value != null) {
                jedis = getSingle();
                jedis.set(key, value);
            } else {
                log.error("easyRedis Single params error,method:SET key:{}  value:{} ", key, value);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public void setex(String key, String value, int seconds) {
        Jedis jedis;
        try {
            if (key != null && value != null && seconds > 0) {
                jedis = getSingle();
                jedis.setex(key, seconds, value);
            } else {
                log.error("easyRedis Single params error,method:SETEX key:{}  value:{} seconds:{} ", key, value, seconds);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public void setex(String key, Object obj, int seconds) {
        Jedis jedis;
        try {
            if (key != null && obj != null && seconds > 0) {
                jedis = getSingle();
                jedis.setex(key.getBytes(StandardCharsets.UTF_8), seconds, serialize(obj));
            } else {
                log.error("easyRedis Single params error,method:SETEX key:{}  obj:{} seconds:{} ", key, obj, seconds);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.get(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public List<String> get(String... keys) {
        Jedis jedis;
        try {
            if (keys != null) {
                jedis = getSingle();
                return jedis.mget(keys);
            } else {
                log.error("easyRedis Single params error,method:GET keys:null");
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public ScanResult<String> scan(int index, String regx) {
        Jedis jedis;
        try {
            if (index > 0 && regx != null) {
                jedis = getSingle();
                ScanParams scanParams = new ScanParams();
                scanParams.match(regx);
                /*查询总数*/
                scanParams.count(Integer.MAX_VALUE);
                return jedis.scan(String.valueOf(index), scanParams);
            } else {
                log.error("easyRedis Single params error,method:SCAN index:{}  regx:{} ", index, regx);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public ScanResult<String> scan(String regx) {
        Jedis jedis;
        try {
            if (regx != null) {
                jedis = getSingle();
                ScanParams scanParams = new ScanParams();
                scanParams.match(regx);
                /*查询总数*/
                scanParams.count(Integer.MAX_VALUE);
                return jedis.scan(ScanParams.SCAN_POINTER_START, scanParams);
            } else {
                log.error("easyRedis Single params error,method:SCAN regx:null");
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis;
        try {
            if (key != null) {
                jedis = getSingle();
                Object obj = unSerialize(jedis.get(key.getBytes(StandardCharsets.UTF_8)));
                return (T) obj;
            } else {
                log.error("easyRedis Single params error,method:GET key:{} clazz:{}", null, clazz);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long getValid(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.ttl(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long removeValid(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.persist(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long getTimeOut(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long delete(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.del(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.incr(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void hset(String key, String field, String value) {
        Jedis jedis;
        try {
            if (key != null && field != null && value != null) {
                jedis = getSingle();
                jedis.hset(key, field, value);
            } else {
                log.error("easyRedis Single params error,method:HSET key:{} field:{} value:{}", key, field, value);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis;
        try {
            if (key != null && field != null) {
                jedis = getSingle();
                return jedis.hget(key, field);
            } else {
                log.error("easyRedis Single params error,method:HGET key:{} field:{}", key, field);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long hdel(String key, String field) {
        Jedis jedis;
        try {
            if (key != null && field != null) {
                jedis = getSingle();
                return jedis.hdel(key, field);
            } else {
                log.error("easyRedis Single params error,method:HDEL key:{} field:{} ", key, field);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return 0L;
    }

    @Override
    public void hmset(String key, Map<String, String> map) {
        Jedis jedis;
        try {
            if (key != null && map != null) {
                jedis = getSingle();
                jedis.hmset(key, map);
            } else {
                log.error("easyRedis Single params error,method:HMSET key:{} map:{} ", key, map);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis;
        try {
            if (key != null && fields != null) {
                jedis = getSingle();
                return jedis.hmget(key, fields);
            } else {
                log.error("easyRedis Single params error,method:HMGET key:{} fields:{} ", key, fields);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Long hlen(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? Long.valueOf(0) : jedis.hlen(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.hgetAll(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void lpush(String key, String... strings) {
        Jedis jedis;
        try {
            if (key != null && strings != null) {
                jedis = getSingle();
                jedis.lpush(key, strings);
            } else {
                log.error("easyRedis Single params error,method:LPUSH key:{} strings:{} ", key, strings);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public void rpush(String key, String... strings) {
        Jedis jedis;
        try {
            if (key != null && strings != null) {
                jedis = getSingle();
                jedis.rpush(key, strings);
            } else {
                log.error("easyRedis Single params error,method:RPUSH key:{} strings:{} ", key, strings);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public Long llen(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? Long.valueOf(0) : jedis.llen(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public String lindex(String key, long index) {
        Jedis jedis;
        try {
            if (key != null && index > 0) {
                jedis = getSingle();
                return jedis.lindex(key, index);
            } else {
                log.error("easyRedis Single params error,method:LINDEX key:{} index:{} ", key, index);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public List<String> lrangeAll(String key) {
        return lrange(key, 0, -1);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis;
        try {
            if (key != null && start > 0 && end > 0) {
                jedis = getSingle();
                return jedis.lrange(key, start, end);
            } else {
                log.error("easyRedis Single params error,method:LRANGE key:{} start:{} end:{}", key, start, end);
            }
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public String lpop(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.lpop(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public String rpop(String key) {
        Jedis jedis;
        try {
            jedis = getSingle();
            return key == null ? null : jedis.rpop(key);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void setTimeOut(String key, int seconds) {
        Jedis jedis;
        try {
            jedis = getSingle();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            close();
        }
    }

    @Override
    public boolean tryLock(String key, String value, long expire) {
        Jedis jedis;
        try {
            jedis = getSingle();
            String result = jedis.set(key, value, "NX", "PX", expire);
            return "OK".equals(result);
        } catch (Exception e) {
            log.error("redis 分布式锁-锁定异常，异常信息：", e);
        }
        return false;
    }

    @Override
    public boolean unLock(String key, String value) {
        String luaScript =
                "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else  return 0 end";
        Jedis jedis;
        try {
            jedis = getSingle();
            Object result = jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value));
            return Long.parseLong(String.valueOf(result)) == 1L;
        } catch (Exception e) {
            log.error("redis 分布式锁-解锁异常，异常信息：", e);
        }
        return false;
    }
    
    @Override
    public boolean tryLockWait(String key, long expireSecond, int waitSecond, String flag) {
        Jedis jedis;
        try {
            jedis = getSingle();
            // 尝试获得锁 如果自身持有锁则可以再次获得
            String result = jedis.set(key, flag, "NX", "PX", expireSecond);
            if ("OK".equals(result)) {
                log.info("easyRedis Single 获取等待锁成功 key：{}  线程标识：{}", key, flag);
                return true;
            }
            //阻塞等待释放锁通知
            String waitKey = BLOCKING_KEY_PREFIX + key;
            List<String> lp = jedis.blpop(waitSecond, waitKey);
            if (lp == null || lp.size() < 1) {
                log.info("easyRedis Single 获取等待锁失败  key：{}  线程标识：{}", key, flag);
                //如果超时则返回锁定失败
                return false;
            }
            return tryLockWait(key, expireSecond, waitSecond, flag);
        } catch (Exception e) {
            log.error("redis 分布式等待锁-解锁异常，异常信息：", e);
            return false;
        } finally {
            close();
        }
    }
    
    @Override
    public boolean unlockWait(String key, String flag) {
        Jedis jedis;
        try {
            jedis = getSingle();
            //删除锁定的key
            String luaScript =
                    "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else  return 0 end";
            Object result = jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(flag));
            long unlock = Long.parseLong(String.valueOf(result));
            if (unlock == 1L) {
                log.info("easyRedis Single 解锁等待锁成功 key：{} 线程标识：{}", key, flag);
                return false;
            }
            //如果锁释放消息队列里没有值 则释放一个信号
            if (jedis.llen(key).intValue() == 0) {
                //通知等待的线程可以继续获得锁
            	 	String waitKey = BLOCKING_KEY_PREFIX + key;
                jedis.rpush(waitKey, "ok");
            }
            return true;
        } catch (Exception e) {
            log.error("redis 分布式锁-解锁异常，异常信息：", e);
            return false;
        } finally {
            close();
        }
    }

    @Override
    public String getThreadFlag() {
        String flag = threadFlag.get();
        if (flag != null && flag.length() > 0) {
            return flag;
        }
        long num = 0;
        Jedis jedis = getSingle();
        try {
            num = jedis.incr(THREAD_FLAG_NUM);
        } catch (Exception e) {
            log.error("easyRedis Single error: ", e);
        } finally {
            jedis.close();
        }
        flag = "" + num;
        threadFlag.set(flag);
        return flag;
    }

    @Override
    public JedisConnectionFactory getConnectionFactory() {

        return RedisBeanUtil.getBean(JedisConnectionFactory.class);
    }

    @Override
    public void close() {
        //把这里注释掉了，不然会有问题
//        jedis.close();
    }
}
