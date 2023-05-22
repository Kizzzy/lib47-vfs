package cn.kizzzy.vfs.provider;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileHandlerProvider;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class FileHandlerProvider implements IFileHandlerProvider {
    
    private final Map<Type, IFileHandler<?>> handlerKvs
        = new HashMap<>();
    
    @Override
    public boolean addHandler(Type type, IFileHandler<?> handler) {
        handlerKvs.put(type, handler);
        return true;
    }
    
    @Override
    public IFileHandler<?> getHandler(Type clazz) {
        return handlerKvs.get(clazz);
    }
}
