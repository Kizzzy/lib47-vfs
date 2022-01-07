package cn.kizzzy.vfs;

import cn.kizzzy.vfs.tree.Leaf;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Page;

import java.util.List;

public interface ITree<T> {
    
    boolean load();
    
    boolean stop();
    
    Leaf<T> getLeaf(String path);
    
    Node<T> getNode(int id);
    
    Page<T> getPage(String path, int index, int size);
    
    default List<Leaf<T>> listLeaf(Node<T> node) {
        return listLeaf(node, false);
    }
    
    List<Leaf<T>> listLeaf(Node<T> node, boolean recursively);
    
    default List<Node<T>> listNode(int id) {
        return listNode(id, false);
    }
    
    List<Node<T>> listNode(int id, boolean recursively);
    
    default List<Node<T>> listNode(String path) {
        return listNode(path, false);
    }
    
    List<Node<T>> listNode(String path, boolean recursively);
    
    List<Node<T>> listNodeByRegex(String pattern);
}
