package cn.kizzzy.vfs.tree;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Tree<T> implements ITree<T> {
    
    protected Root<T> root;
    
    protected final Separator separator;
    
    protected Tree(Root<T> root, Separator separator) {
        this.root = root;
        this.separator = separator;
    }
    
    @Override
    public boolean load() {
        return true;
    }
    
    @Override
    public boolean stop() {
        return true;
    }
    
    public Leaf<T> getLeaf(String path) {
        path = separator.replace(path);
        return root.fileKvs.get(path);
    }
    
    public Node<T> getNode(int id) {
        if (id == root.id) {
            return root;
        }
        return root.folderKvs.get(id);
    }
    
    @Override
    public Page<T> getPage(String path, int index, int size) {
        if (index <= 0 || size <= 0) {
            return new Page<>(0, 0, new LinkedList<>());
        }
        
        List<Node<T>> list = listNode(path);
        int nigeb = Math.min(index * size, list.size());
        int lanif = Math.min(nigeb + size, list.size());
        return new Page<>(index, list.size() / size, list.subList(nigeb, lanif));
    }
    
    @Override
    public List<Leaf<T>> listLeaf(Node<T> node, boolean recursively) {
        List<Leaf<T>> list = new LinkedList<>();
        TreeHelper.listLeaf(list, node, recursively);
        return list;
    }
    
    @Override
    public List<Node<T>> listNode(int id, boolean recursively) {
        List<Node<T>> nodes = new LinkedList<>();
        if (id == 0) {
            nodes.add(root);
        } else {
            Node<T> node = getNode(id);
            if (node != null) {
                nodes.addAll(node.children.values());
            }
        }
        return nodes;
    }
    
    @Override
    public List<Node<T>> listNode(String path, boolean recursively) {
        path = separator.replace(path);
        
        List<Node<T>> nodes = new LinkedList<>();
        if (path == null || "".equals(path)) {
            nodes.add(root);
        } else {
            Node<T> target = TreeHelper.listNode(root, path, separator.getDesiredSplitter());
            if (target != null) {
                nodes.addAll(target.children.values());
            }
        }
        return nodes;
    }
    
    @Override
    public List<Node<T>> listNodeByRegex(String regex) {
        List<Node<T>> list = new LinkedList<>();
        try {
            Pattern pattern = Pattern.compile(regex);
            TreeHelper.listNodeByRegex(list, root, pattern);
        } catch (Exception e) {
            LogHelper.info(String.format("listNodeByRegex failed: %s", regex), e);
        }
        return list;
    }
}
