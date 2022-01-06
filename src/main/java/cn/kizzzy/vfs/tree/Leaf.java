package cn.kizzzy.vfs.tree;

public class Leaf<T> extends Node<T> {
    
    public final T item;
    
    public final String pack;
    
    public final String path;
    
    public Leaf(int id, String name, String pack, String path, T item) {
        super(id, name, true);
        this.pack = pack;
        this.path = path;
        this.item = item;
    }
}
