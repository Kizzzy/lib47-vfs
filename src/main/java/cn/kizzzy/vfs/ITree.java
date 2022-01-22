package cn.kizzzy.vfs;

import cn.kizzzy.vfs.tree.Leaf;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Page;

import java.util.List;

public interface ITree {
    
    boolean load();
    
    boolean stop();
    
    Leaf getLeaf(String path);
    
    Node getNode(int id);
    
    Page getPage(String path, int index, int size);
    
    default List<Leaf> listLeaf(int id) {
        return listLeaf(id, false);
    }
    
    List<Leaf> listLeaf(int id, boolean recursively);
    
    default List<Leaf> listLeaf(Node node) {
        return listLeaf(node, false);
    }
    
    default List<Leaf> listLeaf(Node node, boolean recursively) {
        return listLeaf(node.id, recursively);
    }
    
    default List<Node> listNode(int id) {
        return listNode(id, false);
    }
    
    List<Node> listNode(int id, boolean recursively);
    
    default List<Node> listNode(String path) {
        return listNode(path, false);
    }
    
    List<Node> listNode(String path, boolean recursively);
    
    List<Node> listNodeByRegex(String pattern);
}
