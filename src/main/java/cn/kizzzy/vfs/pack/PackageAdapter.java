package cn.kizzzy.vfs.pack;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.Separator;
import cn.kizzzy.vfs.handler.BytesFileHandler;
import cn.kizzzy.vfs.handler.StringFileHandler;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class PackageAdapter implements IPackage {
    
    protected static final Separator FILE_SEPARATOR
        = new Separator(File.separatorChar, false);
    
    protected final String root;
    
    protected final Map<Type, IFileHandler<?>> handlerKvs
        = new HashMap<>();
    
    public PackageAdapter(String root) {
        this.root = root;
        
        initDefaultHandler();
    }
    
    protected void initDefaultHandler() {
        handlerKvs.put(byte[].class, new BytesFileHandler());
        handlerKvs.put(String.class, new StringFileHandler());
    }
    
    @Override
    public Map<Type, IFileHandler<?>> getHandlerKvs() {
        return handlerKvs;
    }
    
    @Override
    public IFileHandler<?> getHandler(Type clazz) {
        return handlerKvs.get(clazz);
    }
    
    @Override
    public Object load(String path, Type clazz) {
        IFileHandler<?> loader = getHandler(clazz);
        if (loader != null) {
            return load(path, loader);
        }
        return null;
    }
    
    @Override
    public Object load(String path, IFileLoader<?> loader) {
        try {
            if (exist(path)) {
                return loadImpl(path, loader);
            }
        } catch (Exception e) {
            LogHelper.error(String.format("load error: %s", path), e);
        }
        return null;
    }
    
    protected abstract Object loadImpl(String path, IFileLoader<?> loader) throws Exception;
    
    @Override
    public <T> boolean save(String path, T data) {
        IFileHandler<T> saver = (IFileHandler<T>) getHandler(data.getClass());
        if (saver != null) {
            return save(path, data, saver);
        }
        return false;
    }
    
    @Override
    public <T> boolean save(String path, T data, IFileSaver<T> saver) {
        try {
            return saveImpl(path, data, saver);
        } catch (Exception e) {
            LogHelper.error(String.format("save error: %s", path), e);
        }
        return false;
    }
    
    protected abstract <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception;
}
