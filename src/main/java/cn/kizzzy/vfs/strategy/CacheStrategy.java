package cn.kizzzy.vfs.strategy;

import cn.kizzzy.vfs.IStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheStrategy<T, R> implements IStrategy<T, R> {
    
    private final Map<T, R> cache
        = new ConcurrentHashMap<>();
    
    @Override
    public R TryGet(T key) {
        if (key != null) {
            return cache.get(key);
        }
        return null;
    }
    
    @Override
    public void Set(T key, R value) {
        if (key != null && value != null) {
            cache.put(key, value);
        }
    }
}
