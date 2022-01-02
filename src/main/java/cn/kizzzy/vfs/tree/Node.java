package cn.kizzzy.vfs.tree;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
    
    public int id;
    
    private int type;
    
    private boolean leaf;
    
    public String name;
    
    public Node<T> parent;
    
    public Leaf<T> thumbs;
    
    public Map<String, Node<T>> children
        = new HashMap<>();
    
    public Node(String name) {
        this(name, 0, 1, false, null, null);
        id = hashCode();
    }
    
    public Node(String name, Node<T> parent, Leaf<T> thumbs) {
        this(name, 0, 1, false, parent, thumbs);
        id = hashCode();
    }
    
    public Node(String name, int id, boolean leaf) {
        this(name, id, 2, leaf, null, null);
    }
    
    public Node(String name, int id, boolean leaf, Node<T> parent, Leaf<T> thumbs) {
        this(name, id, 2, leaf, parent, thumbs);
    }
    
    public Node(String name, int id, int type, boolean leaf, Node<T> parent, Leaf<T> thumbs) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.leaf = leaf;
        this.parent = parent;
        this.thumbs = thumbs;
    }
    
    public boolean IsLeaf() {
        return type == 1 ? children.isEmpty() : leaf;
    }
}
