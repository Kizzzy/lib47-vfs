package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public abstract class CacheTree<T> implements ITree<T> {
    
    protected final String folder;
    
    protected final ITree<T> tree;
    
    protected final Map<String, Object> loadKvs =
        new ConcurrentHashMap<>();
    
    public CacheTree(String folder, ITree<T> tree) {
        this.folder = folder;
        this.tree = tree;
    }
    
    public <R> R loadFile(String path, Class<R> clazz) {
        return (R) loadFile(path, (Type) clazz);
    }
    
    public Object loadFile(String path, Type clazz) {
        path = path.toLowerCase();
        
        Object file = loadKvs.get(path);
        if (file != null) {
            return file;
        }
        
        Leaf<T> leaf = getFile(path);
        if (leaf == null) {
            return null;
        }
        
        file = leaf2file(leaf, clazz);
        if (file != null) {
            loadKvs.put(path, file);
        }
        
        return file;
    }
    
    protected abstract Object leaf2file(Leaf<T> leaf, Type clazz);
    
    @Override
    public boolean load() {
        return tree.load();
    }
    
    @Override
    public boolean stop() {
        return tree.stop();
    }
    
    @Override
    public Leaf<T> getFile(String path) {
        return tree.getFile(path);
    }
    
    @Override
    public Node<T> getFolder(int id) {
        return tree.getFolder(id);
    }
    
    @Override
    public List<Leaf<T>> getFileByFolder(Node<T> root) {
        return tree.getFileByFolder(root);
    }
    
    @Override
    public List<Node<T>> getFolderByParent(int id) {
        return tree.getFolderByParent(id);
    }
    
    @Override
    public List<Node<T>> getFolderByPath(String path) {
        return tree.getFolderByPath(path);
    }
    
    @Override
    public Page<T> getFolderByPage(String path, int index, int size) {
        return tree.getFolderByPage(path, index, size);
    }
    
    @Override
    public List<Node<T>> getFolderByRegex(String regex) {
        return tree.getFolderByRegex(regex);
    }
}
