package cn.kizzzy.vfs.tree;

import java.util.HashMap;
import java.util.Map;

public class Node {
    
    public final int id;
    
    public final boolean leaf;
    
    public final String name;
    
    public final Map<String, Node> children
        = new HashMap<>();
    
    public Node(int id, String name) {
        this(id, name, false);
    }
    
    public Node(int id, String name, boolean leaf) {
        this.id = id;
        this.leaf = leaf;
        this.name = name;
    }
    
    public boolean accept(String name) {
        return this.name.equals(name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
