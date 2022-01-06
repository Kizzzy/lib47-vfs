package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Tree<T> implements ITree<T> {
    
    protected final Separator separator;
    
    protected Map<String, Leaf<T>> fileKvs =
        new ConcurrentHashMap<>();
    
    protected Map<Integer, Node<T>> folderKvs =
        new ConcurrentHashMap<>();
    
    protected Map<String, List<Node<T>>> pathKvs =
        new ConcurrentHashMap<>();
    
    protected Tree(Separator separator) {
        this.separator = separator;
    }
    
    @Override
    public boolean load() {
        return true;
    }
    
    @Override
    public boolean stop() {
        return true;
    }
    
    public Leaf<T> getLeaf(String path) {
        path = separator.replace(path);
        
        Leaf<T> leaf = fileKvs.get(path);
        if (leaf != null) {
            return leaf;
        }
        
        leaf = getLeafImpl(path);
        if (leaf != null) {
            fileKvs.put(path, leaf);
        }
        return leaf;
    }
    
    protected abstract Leaf<T> getLeafImpl(String path);
    
    public Node<T> getNode(int id) {
        Node<T> node = folderKvs.get(id);
        if (node != null) {
            return node;
        }
        
        node = getNodeImpl(id);
        if (node != null) {
            folderKvs.put(id, node);
        }
        
        return node;
    }
    
    protected abstract Node<T> getNodeImpl(int id);
    
    public List<Node<T>> listNode(String path, boolean recursively) {
        path = separator.replace(path);
        
        List<Node<T>> list = pathKvs.get(path);
        if (list != null) {
            return list;
        }
        
        list = listNodeImpl(path, recursively);
        if (list != null) {
            pathKvs.put(path, list);
        }
        
        return list;
    }
    
    protected abstract List<Node<T>> listNodeImpl(String path, boolean recursively);
}
