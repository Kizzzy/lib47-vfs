package cn.kizzzy.vfs.tree;

import java.util.HashMap;
import java.util.Map;

public class Root<T> extends Node<T> {
    
    public final Map<String, Leaf<T>> fileKvs
        = new HashMap<>();
    
    public final Map<Integer, Node<T>> folderKvs
        = new HashMap<>();
    
    public Root(int id, String name) {
        super(id, name);
    }
    
    @Override
    public boolean accept(String name) {
        return name == null || "".equals(name);
    }
}
