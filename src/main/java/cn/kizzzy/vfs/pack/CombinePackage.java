package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileHandlerProvider;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.provider.FileHandlerProvider;
import cn.kizzzy.vfs.tree.Forest;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class CombinePackage extends Forest implements IPackage {
    
    private final List<IPackage> vfsList
        = new LinkedList<>();
    
    private final IFileHandlerProvider provider
        = new FileHandlerProvider();
    
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
    public boolean addHandler(Type type, IFileHandler<?> handler) {
        return provider.addHandler(type, handler);
    }
    
    @Override
    public IFileHandler<?> getHandler(Type clazz) {
        for (IPackage vfs : vfsList) {
            IFileHandler<?> handler = vfs.getHandler(clazz);
            if (handler != null) {
                return handler;
            }
        }
        return provider.getHandler(clazz);
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
    public IInputStreamGetter getInputStreamGetter(String path) {
        for (IPackage vfs : vfsList) {
            IInputStreamGetter getter = vfs.getInputStreamGetter(path);
            if (getter != null) {
                return getter;
            }
        }
        return null;
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        for (IPackage vfs : vfsList) {
            IOutputStreamGetter getter = vfs.getOutputStreamGetter(path);
            if (getter != null) {
                return getter;
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
