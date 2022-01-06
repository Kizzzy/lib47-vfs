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
    
    @Override
    public boolean load() {
        return tree.load();
    }
    
    @Override
    public boolean stop() {
        return tree.stop();
    }
    
    @Override
    public Leaf<T> getLeaf(String path) {
        return tree.getLeaf(path);
    }
    
    @Override
    public Node<T> getNode(int id) {
        return tree.getNode(id);
    }
    
    @Override
    public Page<T> getPage(String path, int index, int size) {
        return tree.getPage(path, index, size);
    }
    
    @Override
    public List<Leaf<T>> listLeaf(Node<T> node, boolean recursively) {
        return tree.listLeaf(node, recursively);
    }
    
    @Override
    public List<Node<T>> listNode(int id) {
        return tree.listNode(id);
    }
    
    @Override
    public List<Node<T>> listNode(String path, boolean recursively) {
        return tree.listNode(path, recursively);
    }
    
    @Override
    public List<Node<T>> listNodeByRegex(String regex) {
        return tree.listNodeByRegex(regex);
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
        
        Leaf<T> leaf = getLeaf(path);
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
}
