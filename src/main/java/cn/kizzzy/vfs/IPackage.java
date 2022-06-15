package cn.kizzzy.vfs;

import java.lang.reflect.Type;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface IPackage extends ITree {
    
    Map<Type, IFileHandler<?>> getHandlerKvs();
    
    IFileHandler<?> getHandler(Type clazz);
    
    default <T> IFileHandler<T> getHandler(Class<T> clazz) {
        return (IFileHandler<T>) getHandler((Type) clazz);
    }
    
    boolean exist(String path);
    
    IStreamable getStreamable(String path);
    
    default <T> T load(String path, Class<T> clazz) {
        return (T) load(path, (Type) clazz);
    }
    
    default Object load(String path, Type clazz) {
        IFileHandler<?> loader = getHandler(clazz);
        if (loader != null) {
            return load(path, loader);
        }
        return null;
    }
    
    Object load(String path, IFileLoader<?> loader);
    
    default <T> boolean save(String path, T data) {
        IFileHandler<T> saver = (IFileHandler<T>) getHandler(data.getClass());
        if (saver != null) {
            return save(path, data, saver);
        }
        return false;
    }
    
    <T> boolean save(String path, T data, IFileSaver<T> saver);
}
