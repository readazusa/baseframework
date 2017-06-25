package cn.com.oceansoft.base.service.cache;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by smc
 * date on 2016/3/13.
 * Email:sunmch@163.com
 * 缓存处理类
 */

@Service
public class BaseCacheService {

    @Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * 保存K-V
     * @param key   String 键值
     * @param value String 值
     */
    public void set(final String key, final String value) {
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                redisConnection.set(serializer.serialize(key), serializer.serialize(value));
                return null;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param seconds 过期时间，单位秒
     */
    public void set(final String key, final String value, long seconds) {
            redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    redisConnection.setEx(serializer.serialize(key),seconds,serializer.serialize(value));
                    return null;
                }
            });
    }

    /**
     * @param key
     * @param value
     * @param day   过期时间天数
     */
    public void setDay(final String key, final String value, int day) {
        set(key,value,day*24*3600);
    }

    public void setHour(final String key, final String value, int hour) {
        set(key,value,hour*3600);
    }

    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return serializer.deserialize(redisConnection.get(serializer.serialize(key)));
            }
        });
    }

    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return redisConnection.exists(serializer.serialize(key));
            }
        });
    }

    public void setObject(final String key, final Object obj) {
        this.set(key, JSON.toJSONString(obj));
    }

    public void setObject(final String key,final Object obj,long seconds){
        this.set(key,JSON.toJSONString(obj),seconds);
    }

    public void setObjectDay(final String key,final Object obj,int day){
        this.setDay(key, JSON.toJSONString(obj), day);
    }

    public void setObjectHour(final String key,final Object obj,int hour){
        this.setHour(key,JSON.toJSONString(obj),hour);
    }

    public <T> T getObject(final String key, final Class<T> cls) {
        String value = get(key);
        if (StringUtils.isNotBlank(value)) {
            return JSON.parseObject(value, cls);
        }
        return null;
    }


    public void clear(String key){
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        redisTemplate.delete(serializer.serialize(key));
    }


}
