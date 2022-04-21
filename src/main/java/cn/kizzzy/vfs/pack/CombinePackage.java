package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.tree.Leaf;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Page;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CombinePackage implements IPackage {
    
    private IPackage[] vfs;
    
    public CombinePackage(IPackage... vfs) {
        this.vfs = vfs;
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
        for (IPackage vfs : vfs) {
            if (vfs.exist(path)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Object load(String path, Type clazz) {
        for (IPackage vfs : vfs) {
            Object obj = vfs.load(path, clazz);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
    
    @Override
    public Object load(String path, IFileLoader<?> loader) {
        for (IPackage vfs : vfs) {
            Object obj = vfs.load(path, loader);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
    
    @Override
    public <T> boolean save(String path, T data) {
        for (IPackage vfs : vfs) {
            if (vfs.save(path, data)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public <T> boolean save(String path, T data, IFileSaver<T> saver) {
        for (IPackage vfs : vfs) {
            if (vfs.save(path, saver)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean load() {
        for (IPackage vfs : vfs) {
            if (!vfs.load()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean stop() {
        for (IPackage vfs : vfs) {
            if (!vfs.stop()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Leaf getLeaf(String path) {
        for (IPackage vfs : vfs) {
            Leaf leaf = vfs.getLeaf(path);
            if (leaf != null) {
                return leaf;
            }
        }
        return null;
    }
    
    @Override
    public Node getNode(int id) {
        for (IPackage vfs : vfs) {
            Node node = vfs.getNode(id);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
    
    @Override
    public Page getPage(String path, int index, int size) {
        for (IPackage vfs : vfs) {
            Page page = vfs.getPage(path, index, size);
            if (page != null) {
                return page;
            }
        }
        return null;
    }
    
    @Override
    public List<Leaf> listLeaf(int id, boolean recursively) {
        for (IPackage vfs : vfs) {
            List<Leaf> leaves = vfs.listLeaf(id, recursively);
            if (leaves != null) {
                return leaves;
            }
        }
        return null;
    }
    
    @Override
    public List<Node> listNode(int id, boolean recursively) {
        for (IPackage vfs : vfs) {
            List<Node> nodes = vfs.listNode(id, recursively);
            if (nodes != null) {
                return nodes;
            }
        }
        return null;
    }
    
    @Override
    public List<Node> listNode(String path, boolean recursively) {
        for (IPackage vfs : vfs) {
            List<Node> nodes = vfs.listNode(path, recursively);
            if (nodes != null) {
                return nodes;
            }
        }
        return null;
    }
    
    @Override
    public List<Node> listNodeByRegex(String pattern) {
        for (IPackage vfs : vfs) {
            List<Node> nodes = vfs.listNodeByRegex(pattern);
            if (nodes != null) {
                return nodes;
            }
        }
        return null;
    }
}
