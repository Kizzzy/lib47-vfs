package cn.kizzzy.vfs;

import java.util.List;

public class ListNode {
    
    public String root;
    
    public String name;
    
    public String path;
    
    public List<ListNode> children;
    
    @Override
    public String toString() {
        return "ListNode{" +
            "root='" + root + '\'' +
            ", name='" + name + '\'' +
            ", path='" + path + '\'' +
            '}';
    }
}
