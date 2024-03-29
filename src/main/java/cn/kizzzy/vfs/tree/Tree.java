package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Tree implements ITree {
    
    protected static final Logger logger = LoggerFactory.getLogger(Tree.class);
    
    protected Root root;
    
    protected final Separator separator;
    
    protected Tree(Root root, Separator separator) {
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
    
    @Override
    public Leaf getLeaf(int id) {
        Node node = getNode(id);
        if (node.leaf) {
            return (Leaf) node;
        }
        return null;
    }
    
    @Override
    public Leaf getLeaf(String path) {
        path = separator.replace(path);
        return root.fileKvs.get(path);
    }
    
    @Override
    public Node getNode(int id) {
        if (id == root.id || id == 0) {
            return root;
        }
        return root.folderKvs.get(id);
    }
    
    @Override
    public Page getPage(String path, int index, int size) {
        if (index <= 0 || size <= 0) {
            return new Page(0, 0, new LinkedList<>());
        }
        
        List<Node> list = listNode(path);
        int nigeb = Math.min(index * size, list.size());
        int lanif = Math.min(nigeb + size, list.size());
        return new Page(index, list.size() / size, list.subList(nigeb, lanif));
    }
    
    @Override
    public List<Leaf> listLeaf(int id, boolean recursively) {
        List<Leaf> list = new LinkedList<>();
        Node node = getNode(id);
        if (node != null) {
            TreeHelper.listLeaf(list, node, recursively);
        }
        return list;
    }
    
    @Override
    public List<Node> listNode(int id, boolean recursively) {
        List<Node> nodes = new LinkedList<>();
        if (id == 0) {
            nodes.add(root);
        } else {
            Node node = getNode(id);
            if (node != null) {
                nodes.addAll(node.children.values());
            }
        }
        return nodes;
    }
    
    @Override
    public List<Node> listNode(String path, boolean recursively) {
        path = separator.replace(path);
        
        List<Node> nodes = new LinkedList<>();
        if (path == null || "".equals(path)) {
            nodes.add(root);
        } else {
            Node target = TreeHelper.listNode(root, path, separator.getDesiredSplitter());
            if (target != null) {
                nodes.addAll(target.children.values());
            }
        }
        return nodes;
    }
    
    @Override
    public List<Node> listNodeByRegex(String regex) {
        List<Node> list = new LinkedList<>();
        try {
            Pattern pattern = Pattern.compile(regex);
            TreeHelper.listNodeByRegex(list, root, pattern);
        } catch (Exception e) {
            logger.error("listNodeByRegex failed: {}", regex, e);
        }
        return list;
    }
}
