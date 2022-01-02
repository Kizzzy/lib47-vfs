package cn.kizzzy.vfs.strategy;

import cn.kizzzy.vfs.IStrategy;

public class NopStrategy<T, R> implements IStrategy<T, R> {
    
    @Override
    public R TryGet(T key) {
        return null;
    }
    
    @Override
    public void Set(T key, R value) {
    
    }
}
