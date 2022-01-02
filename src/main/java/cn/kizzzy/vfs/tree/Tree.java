package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Tree<T> implements ITree<T> {
    
    protected Map<String, Leaf<T>> fileKvs =
        new ConcurrentHashMap<>();
    
    protected Map<Integer, Node<T>> folderKvs =
        new ConcurrentHashMap<>();
    
    protected Map<String, List<Node<T>>> pageKvs =
        new ConcurrentHashMap<>();
    
    @Override
    public boolean load() {
        return true;
    }
    
    @Override
    public boolean stop() {
        return true;
    }
    
    @Override
    public Leaf<T> getFile(String path) {
        path = path.toLowerCase();
        
        Leaf<T> leaf = fileKvs.get(path);
        if (leaf != null) {
            return leaf;
        }
        
        leaf = getFileImpl(path);
        if (leaf != null) {
            fileKvs.put(path, leaf);
        }
        return leaf;
    }
    
    protected abstract Leaf<T> getFileImpl(String path);
    
    @Override
    public Node<T> getFolder(int id) {
        Node<T> node = folderKvs.get(id);
        if (node != null) {
            return node;
        }
        
        node = getFolderImpl(id);
        if (node != null) {
            folderKvs.put(id, node);
        }
        
        return node;
    }
    
    protected abstract Node<T> getFolderImpl(int id);
    
    @Override
    public List<Node<T>> getFolderByPath(String path) {
        path = path.toLowerCase();
        
        List<Node<T>> list = pageKvs.get(path);
        if (list != null) {
            return list;
        }
        
        list = getFolderByPathImpl(path);
        if (list != null) {
            pageKvs.put(path, list);
        }
        
        return list;
    }
    
    protected abstract List<Node<T>> getFolderByPathImpl(String path);
}
