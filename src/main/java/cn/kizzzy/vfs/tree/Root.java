package cn.kizzzy.vfs.tree;

import java.util.HashMap;
import java.util.Map;

public class Root extends Node {
    
    public final Map<String, Leaf> fileKvs
        = new HashMap<>();
    
    public final Map<Integer, Node> folderKvs
        = new HashMap<>();
    
    public Root(int id, String name) {
        super(id, name);
    }
    
    @Override
    public boolean accept(String name) {
        return name == null || "".equals(name);
    }
}
