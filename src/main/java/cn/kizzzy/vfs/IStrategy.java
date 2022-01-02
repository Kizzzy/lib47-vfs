package cn.kizzzy.vfs;

public interface IStrategy<T, R> {
    
    R TryGet(T key);
    
    void Set(T key, R value);
}
