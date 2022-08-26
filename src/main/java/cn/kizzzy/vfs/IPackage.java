package cn.kizzzy.vfs;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;

import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public interface IPackage extends ITree, IStreamGetterFactory {
    
    default <T> boolean addHandler(Class<T> clazz, IFileHandler<T> handler) {
        return addHandler((Type) clazz, handler);
    }
    
    boolean addHandler(Type type, IFileHandler<?> handler);
    
    default <T> IFileHandler<T> getHandler(Class<T> clazz) {
        return (IFileHandler<T>) getHandler((Type) clazz);
    }
    
    IFileHandler<?> getHandler(Type clazz);
    
    default boolean exist(String path) {
        return getLeaf(path) != null;
    }
    
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
    
    default boolean copyTo(IPackage target, String path) {
        try (IFullyReader reader = getInputStreamGetter(path).getInput();
             IFullyWriter writer = target.getOutputStreamGetter(path).getOutput()) {
            reader.copyTo(writer);
            return true;
        } catch (Exception e) {
            LogHelper.error("copy error: ", e);
            return false;
        }
    }
}
