package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;

import java.util.LinkedList;
import java.util.List;

public class EmptyTree implements ITree {
    @Override
    public boolean load() {
        return true;
    }
    
    @Override
    public boolean stop() {
        return true;
    }
    
    @Override
    public Leaf getLeaf(String path) {
        return null;
    }
    
    @Override
    public Node getNode(int id) {
        return null;
    }
    
    @Override
    public Page getPage(String path, int index, int size) {
        return new Page(0, 0, new LinkedList<>());
    }
    
    @Override
    public List<Leaf> listLeaf(int id, boolean recursively) {
        return new LinkedList<>();
    }
    
    @Override
    public List<Node> listNode(int id, boolean recursively) {
        return new LinkedList<>();
    }
    
    @Override
    public List<Node> listNode(String path, boolean recursively) {
        return new LinkedList<>();
    }
    
    @Override
    public List<Node> listNodeByRegex(String pattern) {
        return new LinkedList<>();
    }
}
