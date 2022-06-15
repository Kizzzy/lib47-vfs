package cn.kizzzy.vfs.handler;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileHandlerContainer;

import java.lang.reflect.Type;

public class EmptyFileHandlerContainer implements IFileHandlerContainer {
    
    @Override
    public boolean addHandler(Type type, IFileHandler<?> handler) {
        return true;
    }
    
    @Override
    public IFileHandler<?> getHandler(Type clazz) {
        return null;
    }
}
