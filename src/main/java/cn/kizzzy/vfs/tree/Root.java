package cn.kizzzy.vfs.tree;

import java.util.HashMap;
import java.util.Map;

public class Root<T> extends Node<T> {
    
    public final String path;
    
    public final Map<String, Leaf<T>> fileKvs
        = new HashMap<>();
    
    public final Map<Integer, Node<T>> folderKvs
        = new HashMap<>();
    
    public Root(String name) {
        super(name);
        path = name;
    }
}
