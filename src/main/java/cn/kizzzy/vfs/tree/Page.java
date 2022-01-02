package cn.kizzzy.vfs.tree;

import java.util.List;

public class Page<T> {
    
    public int index;
    
    public int total;
    
    public List<Node<T>> items;
    
    public Page(int index, int total, List<Node<T>> items) {
        this.index = index;
        this.total = total;
        this.items = items;
    }
}
