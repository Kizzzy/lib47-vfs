package cn.kizzzy.vfs.tree;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.Separator;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class LocalTree<T> extends Tree<T> {
    
    private final Collection<Root<T>> roots;
    
    public LocalTree(Root<T> root, Separator separator) {
        this(Collections.singletonList(root), separator);
    }
    
    public LocalTree(Collection<Root<T>> roots, Separator separator) {
        super(separator);
        this.roots = roots;
    }
    
    @Override
    protected Leaf<T> getLeafImpl(String path) {
        for (Root<T> root : roots) {
            Leaf<T> leaf = root.fileKvs.get(path);
            if (leaf != null) {
                return leaf;
            }
        }
        return null;
    }
    
    @Override
    protected Node<T> getNodeImpl(int id) {
        for (Root<T> root : roots) {
            if (root.id == id) {
                return root;
            }
            Node<T> node = root.folderKvs.get(id);
            if (node != null) {
                return node;
            }
        }
        return null;
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
        if (id == 0) {
            return new LinkedList<>(roots);
        }
        
        Node<T> node = getNodeImpl(id);
        if (node == null) {
            return new LinkedList<>();
        }
        
        return new LinkedList<>(node.children.values());
    }
    
    @Override
    public List<Node<T>> listNode(String path, boolean recursively) {
        if (path == null || "".equals(path)) {
            return new LinkedList<>(roots);
        }
        
        List<Node<T>> list = new LinkedList<>();
        for (Node<T> root : roots) {
            Node<T> target = TreeHelper.listNode(root, path, separator.getDesiredSplitter());
            if (target != null) {
                list.addAll(target.children.values());
            }
        }
        return list;
    }
    
    @Override
    public List<Node<T>> listNodeByRegex(String pattern) {
        List<Node<T>> list = new LinkedList<>();
        try {
            Pattern regex = Pattern.compile(pattern);
            for (Node<T> root : roots) {
                TreeHelper.listNodeByRegex(list, root, regex);
            }
        } catch (Exception e) {
            LogHelper.info(String.format("listNodeByRegex failed: %s", pattern), e);
        }
        return list;
    }
}
