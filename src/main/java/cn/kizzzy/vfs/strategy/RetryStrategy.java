package cn.kizzzy.vfs.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RetryStrategy<T, R> extends CacheStrategy<T, R> {
    
    private final int retryCount;
    
    private final Map<T, Integer> countKvs
        = new ConcurrentHashMap<>();
    
    public RetryStrategy(int retryCount) {
        this.retryCount = retryCount;
    }
    
    @Override
    public R TryGet(T key) {
        if (key != null) {
            int count = countKvs.computeIfAbsent(key, k -> 0);
            if (count <= retryCount) {
                R value = super.TryGet(key);
                if (value != null) {
                    return value;
                }
                countKvs.put(key, count + 1);
            }
        }
        return null;
    }
}
