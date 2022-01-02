package cn.kizzzy.vfs.pack;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.IStrategy;
import cn.kizzzy.vfs.ListNode;
import cn.kizzzy.vfs.ListParameter;
import cn.kizzzy.vfs.handler.BytesFileHandler;
import cn.kizzzy.vfs.handler.StringFileHandler;
import cn.kizzzy.vfs.strategy.NopStrategy;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class PackageAdapter implements IPackage {
    
    private static final char SLASH = '/';
    
    private static final char BACKSLASH = '\\';
    
    protected final char undesired;
    
    protected final char separator;
    
    protected final String root;
    
    protected final IStrategy<String, Object> strategy;
    
    protected final Map<Type, IFileHandler<?>> handlerKvs
        = new HashMap<>();
    
    public PackageAdapter(String root, char separator, IStrategy<String, Object> strategy) {
        this.separator = separator;
        this.undesired = SLASH == separator ? BACKSLASH : SLASH;
        this.root = root.replace(undesired, separator);
        this.strategy = strategy != null ? strategy : new NopStrategy<>();
        
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
    
    public ListNode list(String path, ListParameter param) {
        try {
            if (param == null) {
                param = new ListParameter();
            }
            return listImpl(path.replace(undesired, separator), param);
        } catch (Exception e) {
            LogHelper.error(null, e);
        }
        return null;
    }
    
    protected abstract ListNode listImpl(String path, ListParameter param) throws Exception;
    
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
        Object value = null;
        try {
            value = strategy.TryGet(path);
            if (value != null) {
                return value;
            }
            if (exist(path)) {
                value = loadImpl(path, loader);
            }
            strategy.Set(path, value);
        } catch (Exception e) {
            LogHelper.error(null, e);
        }
        return value;
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
            LogHelper.error(null, e);
        }
        return false;
    }
    
    protected abstract <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception;
}
