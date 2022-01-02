package cn.kizzzy.vfs.tree;

import cn.kizzzy.helper.LogHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class LocalTree<T> extends Tree<T> {
    
    private final Collection<Root<T>> roots;
    
    public LocalTree(Root<T> root) {
        this(Collections.singletonList(root));
    }
    
    public LocalTree(Collection<Root<T>> roots) {
        this.roots = roots;
    }
    
    @Override
    protected Leaf<T> getFileImpl(String path) {
        for (Root<T> root : roots) {
            Leaf<T> leaf = root.fileKvs.get(path);
            if (leaf != null) {
                return leaf;
            }
        }
        return null;
    }
    
    @Override
    protected Node<T> getFolderImpl(int id) {
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
    public List<Leaf<T>> getFileByFolder(Node<T> root) {
        List<Leaf<T>> list = new LinkedList<>();
        TreeHelper.ListFileByFolder(root, list);
        return list;
    }
    
    @Override
    public List<Node<T>> getFolderByParent(int id) {
        if (id == 0) {
            return new LinkedList<>(roots);
        }
        
        Node<T> folder = getFolderImpl(id);
        if (folder == null) {
            return new LinkedList<>();
        }
        
        return new LinkedList<>(folder.children.values());
    }
    
    @Override
    protected List<Node<T>> getFolderByPathImpl(String path) {
        List<Node<T>> list = new LinkedList<>();
        for (Node<T> folder : roots) {
            Node<T> target = TreeHelper.ListFolderByPath(folder, path, "\\");
            if (target != null) {
                list.addAll(target.children.values());
            }
        }
        return list;
    }
    
    @Override
    public Page<T> getFolderByPage(String path, int index, int size) {
        if (index <= 0 || size <= 0) {
            return new Page<>(0, 0, new LinkedList<>());
        }
        
        List<Node<T>> list = getFolderByPath(path);
        int nigeb = Math.min(index * size, list.size());
        int lanif = Math.min(nigeb + size, list.size());
        return new Page<>(index, list.size() / size, list.subList(nigeb, lanif));
    }
    
    @Override
    public List<Node<T>> getFolderByRegex(String pattern) {
        List<Node<T>> list = new LinkedList<>();
        try {
            Pattern regex = Pattern.compile(pattern);
            for (Node<T> folder : roots) {
                TreeHelper.ListFolderByRegex(folder, regex, list);
            }
        } catch (Exception e) {
            LogHelper.debug("GetFolderByRegex Failed: {0}", e);
        }
        return list;
    }
}
