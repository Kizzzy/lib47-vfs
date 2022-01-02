package cn.kizzzy.vfs;

import cn.kizzzy.vfs.tree.Leaf;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Page;

import java.util.List;

public interface ITree<T> {
    
    boolean load();
    
    boolean stop();
    
    Leaf<T> getFile(String path);
    
    Node<T> getFolder(int id);
    
    List<Leaf<T>> getFileByFolder(Node<T> root);
    
    List<Node<T>> getFolderByParent(int id);
    
    List<Node<T>> getFolderByRegex(String pattern);
    
    List<Node<T>> getFolderByPath(String path);
    
    Page<T> getFolderByPage(String path, int index, int size);
}
