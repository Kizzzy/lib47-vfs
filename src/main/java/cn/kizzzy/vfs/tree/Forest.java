package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;

import java.util.LinkedList;
import java.util.List;

public class Forest<T> implements ITree<T> {
    
    private final Iterable<ITree<T>> trees;
    
    public Forest(Iterable<ITree<T>> trees) {
        this.trees = trees;
    }
    
    @Override
    public boolean load() {
        for (ITree<T> tree : trees) {
            if (!tree.load()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean stop() {
        for (ITree<T> tree : trees) {
            if (!tree.stop()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Leaf<T> getLeaf(String path) {
        for (ITree<T> tree : trees) {
            Leaf<T> leaf = tree.getLeaf(path);
            if (leaf != null) {
                return leaf;
            }
        }
        return null;
    }
    
    @Override
    public Node<T> getNode(int id) {
        for (ITree<T> tree : trees) {
            Node<T> node = tree.getNode(id);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
    
    @Override
    public Page<T> getPage(String path, int index, int size) {
        for (ITree<T> tree : trees) {
            Page<T> node = tree.getPage(path, index, size);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
    
    @Override
    public List<Leaf<T>> listLeaf(Node<T> node, boolean recursively) {
        List<Leaf<T>> list = new LinkedList<>();
        for (ITree<T> tree : trees) {
            list.addAll(tree.listLeaf(node, recursively));
        }
        return list;
    }
    
    @Override
    public List<Node<T>> listNode(int id, boolean recursively) {
        List<Node<T>> list = new LinkedList<>();
        for (ITree<T> tree : trees) {
            list.addAll(tree.listNode(id, recursively));
        }
        return list;
    }
    
    @Override
    public List<Node<T>> listNode(String path, boolean recursively) {
        List<Node<T>> list = new LinkedList<>();
        for (ITree<T> tree : trees) {
            list.addAll(tree.listNode(path, recursively));
        }
        return list;
    }
    
    @Override
    public List<Node<T>> listNodeByRegex(String regex) {
        List<Node<T>> list = new LinkedList<>();
        for (ITree<T> tree : trees) {
            list.addAll(tree.listNodeByRegex(regex));
        }
        return list;
    }
}
