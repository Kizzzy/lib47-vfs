package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;

import java.util.LinkedList;
import java.util.List;

public class Forest implements ITree {
    
    private final List<ITree> trees
        = new LinkedList<>();
    
    public Forest(ITree... trees) {
        for (ITree tree : trees) {
            addTree(tree);
        }
    }
    
    public Forest(List<ITree> trees) {
        for (ITree tree : trees) {
            addTree(tree);
        }
    }
    
    @Override
    public boolean load() {
        for (ITree tree : trees) {
            if (!tree.load()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean stop() {
        for (ITree tree : trees) {
            if (!tree.stop()) {
                return false;
            }
        }
        return true;
    }
    
    public void addTree(ITree tree) {
        trees.add(tree);
    }
    
    public void removeTree(ITree tree) {
        trees.remove(tree);
    }
    
    @Override
    public Leaf getLeaf(int id) {
        for (ITree tree : trees) {
            Leaf leaf = tree.getLeaf(id);
            if (leaf != null) {
                return leaf;
            }
        }
        return null;
    }
    
    @Override
    public Leaf getLeaf(String path) {
        for (ITree tree : trees) {
            Leaf leaf = tree.getLeaf(path);
            if (leaf != null) {
                return leaf;
            }
        }
        return null;
    }
    
    @Override
    public Node getNode(int id) {
        for (ITree tree : trees) {
            Node node = tree.getNode(id);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
    
    @Override
    public Page getPage(String path, int index, int size) {
        for (ITree tree : trees) {
            Page node = tree.getPage(path, index, size);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
    
    @Override
    public List<Leaf> listLeaf(int id, boolean recursively) {
        List<Leaf> list = new LinkedList<>();
        for (ITree tree : trees) {
            list.addAll(tree.listLeaf(id, recursively));
        }
        return list;
    }
    
    @Override
    public List<Node> listNode(int id, boolean recursively) {
        List<Node> list = new LinkedList<>();
        for (ITree tree : trees) {
            list.addAll(tree.listNode(id, recursively));
        }
        return list;
    }
    
    @Override
    public List<Node> listNode(String path, boolean recursively) {
        List<Node> list = new LinkedList<>();
        for (ITree tree : trees) {
            list.addAll(tree.listNode(path, recursively));
        }
        return list;
    }
    
    @Override
    public List<Node> listNodeByRegex(String regex) {
        List<Node> list = new LinkedList<>();
        for (ITree tree : trees) {
            list.addAll(tree.listNodeByRegex(regex));
        }
        return list;
    }
}
