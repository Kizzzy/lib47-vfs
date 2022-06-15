package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.tree.Forest;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CombinePackage extends Forest implements IPackage {
    
    private final List<IPackage> vfsList = new LinkedList<>();
    
    public CombinePackage(IPackage... vfsList) {
        for (IPackage vfs : vfsList) {
            addPackage(vfs);
        }
    }
    
    public CombinePackage(List<IPackage> vfsList) {
        for (IPackage vfs : vfsList) {
            addPackage(vfs);
        }
    }
    
    public void addPackage(IPackage vfs) {
        vfsList.add(vfs);
        addTree(vfs);
    }
    
    public void removePackage(IPackage vfs) {
        vfsList.remove(vfs);
        removeTree(vfs);
    }
    
    @Override
    public Map<Type, IFileHandler<?>> getHandlerKvs() {
        return null;
    }
    
    @Override
    public IFileHandler<?> getHandler(Type clazz) {
        return null;
    }
    
    @Override
    public boolean exist(String path) {
        for (IPackage vfs : vfsList) {
            if (vfs.exist(path)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public IStreamable getStreamable(String path) {
        for (IPackage vfs : vfsList) {
            IStreamable streamable = vfs.getStreamable(path);
            if (streamable != null) {
                return streamable;
            }
        }
        return null;
    }
    
    @Override
    public Object load(String path, Type clazz) {
        for (IPackage vfs : vfsList) {
            Object obj = vfs.load(path, clazz);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
    
    @Override
    public Object load(String path, IFileLoader<?> loader) {
        for (IPackage vfs : vfsList) {
            Object obj = vfs.load(path, loader);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
    
    @Override
    public <T> boolean save(String path, T data) {
        for (IPackage vfs : vfsList) {
            if (vfs.save(path, data)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public <T> boolean save(String path, T data, IFileSaver<T> saver) {
        for (IPackage vfs : vfsList) {
            if (vfs.save(path, saver)) {
                return true;
            }
        }
        return false;
    }
}
