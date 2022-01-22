package cn.kizzzy.vfs.tree;

import java.util.List;

public class Page {
    
    public int index;
    
    public int total;
    
    public List<Node> items;
    
    public Page(int index, int total, List<Node> items) {
        this.index = index;
        this.total = total;
        this.items = items;
    }
}
