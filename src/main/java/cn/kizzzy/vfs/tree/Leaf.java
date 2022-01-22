package cn.kizzzy.vfs.tree;

public class Leaf extends Node {
    
    public final Object item;
    
    public final String pack;
    
    public final String path;
    
    public Leaf(int id, String name, String pack, String path, Object item) {
        super(id, name, true);
        this.pack = pack;
        this.path = path;
        this.item = item;
    }
}
