package cn.kizzzy.vfs.strategy;

import cn.kizzzy.vfs.IStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruStrategy<T, R> implements IStrategy<T, R> {
    
    private final LinkedHashMap<T, R> lru;
    
    public LruStrategy(int capacity) {
        lru = new LinkedHashMap<T, R>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    
    @Override
    public R TryGet(T key) {
        if (key != null) {
            return lru.get(key);
        }
        return null;
    }
    
    @Override
    public void Set(T key, R value) {
        if (key != null && value != null) {
            lru.put(key, value);
        }
    }
}
