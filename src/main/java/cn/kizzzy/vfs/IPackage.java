package cn.kizzzy.vfs;

import java.lang.reflect.Type;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface IPackage {
    
    Map<Type, IFileHandler<?>> getHandlerKvs();
    
    IFileHandler<?> getHandler(Type clazz);
    
    default <T> IFileHandler<T> getHandler(Class<T> clazz) {
        return (IFileHandler<T>) getHandler((Type) clazz);
    }
    
    default ListNode list(String path) {
        return list(path, null);
    }
    
    ListNode list(String path, ListParameter param);
    
    boolean exist(String path);
    
    Object load(String path, Type clazz);
    
    Object load(String path, IFileLoader<?> loader);
    
    default <T> T load(String path, Class<T> clazz) {
        return (T) load(path, (Type) clazz);
    }
    
    <T> boolean save(String path, T data);
    
    <T> boolean save(String path, T data, IFileSaver<T> saver);
}
