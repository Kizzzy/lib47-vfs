package cn.kizzzy.vfs.handler;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileHandlerContainer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class FileHandlerContainer implements IFileHandlerContainer {
    
    protected final Map<Type, IFileHandler<?>> handlerKvs
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
