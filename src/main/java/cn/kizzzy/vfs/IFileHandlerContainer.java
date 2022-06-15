package cn.kizzzy.vfs;

import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public interface IFileHandlerContainer {
    
    default <T> boolean addHandler(Class<T> clazz, IFileHandler<T> handler) {
        return addHandler((Type) clazz, handler);
    }
    
    boolean addHandler(Type type, IFileHandler<?> handler);
    
    default <T> IFileHandler<T> getHandler(Class<T> clazz) {
        return (IFileHandler<T>) getHandler((Type) clazz);
    }
    
    IFileHandler<?> getHandler(Type clazz);
}
