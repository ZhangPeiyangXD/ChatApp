package com.itzpy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component("redisUtils")
public class RedisUtils {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置指定key的值
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置指定key的值并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间(秒)
     */
    public void setex(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 获取指定key的值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定key的值
     *
     * @param key 键
     * @return true-删除成功，false-删除失败
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    public Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true-存在，false-不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return true-设置成功，false-设置失败
     */
    public Boolean expire(String key, long time) {
        if (time > 0) {
            return redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * 获取过期时间
     *
     * @param key 键
     * @return 时间(秒)，返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 递增步长
     * @return 递增后的值
     */
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 递减步长
     * @return 递减后的值
     */
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * 向指定key的hash中添加键值对
     *
     * @param key   键
     * @param hashKey hash键
     * @param value 值
     */
    public void hSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取指定key的hash中的值
     *
     * @param key   键
     * @param hashKey hash键
     * @return 值
     */
    public Object hGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 删除指定key的hash中的键值对
     *
     * @param key   键
     * @param hashKeys hash键数组
     * @return 删除的个数
     */
    public Long hDelete(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 判断指定key的hash中是否存在某键
     *
     * @param key   键
     * @param hashKey hash键
     * @return true-存在，false-不存在
     */
    public Boolean hHasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 向指定key的set中添加元素
     *
     * @param key   键
     * @param values 值数组
     * @return 添加成功的个数
     */
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取指定key的set的大小
     *
     * @param key 键
     * @return set的大小
     */
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断指定key的set中是否包含某元素
     *
     * @param key   键
     * @param value 值
     * @return true-包含，false-不包含
     */
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取指定key的set中的所有元素
     *
     * @param key 键
     * @return 元素集合
     */
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 从指定key的set中随机弹出一个元素
     *
     * @param key 键
     * @return 弹出的元素
     */
    public Object sPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 从指定key的set中移除元素
     *
     * @param key   键
     * @param values 值数组
     * @return 移除的个数
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }
}