package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

public abstract class TreeBuilderAdapter<T, R> extends TreeBuilder {
    
    protected interface Helper<T, R> {
        
        String idxPath(T idxFile);
        
        Iterable<R> entries(T idxFile);
        
        String itemPath(R item);
    }
    
    public TreeBuilderAdapter(Separator separator, IdGenerator idGenerator) {
        super(separator, idGenerator);
    }
    
    protected ITree buildImpl(T idxFile, Helper<T, R> helper) {
        try {
            Root root = new Root(idGenerator.getId(), helper.idxPath(idxFile));
            for (R file : helper.entries(idxFile)) {
                listImpl(root, root, file, helper);
            }
            return new Tree(root, separator);
        } catch (Exception e) {
            logger.error(String.format("build tree failed: %s", helper.idxPath(idxFile)), e);
            return null;
        }
    }
    
    protected void listImpl(Root root, Node parent, R item, Helper<T, R> helper) {
        String raw_path = helper.itemPath(item);
        if (raw_path == null) {
            return;
        }
        
        String[] names = separator.split(raw_path);
        int i = 0;
        for (String name : names) {
            Node child = parent.children.get(name);
            if (child == null) {
                if (i == names.length - 1) {
                    String path = separator.replace(raw_path);
                    Leaf leaf = new Leaf(idGenerator.getId(), name, root.name, path, item);
                    root.fileKvs.put(leaf.path, leaf);
                    child = leaf;
                } else {
                    child = new Node(idGenerator.getId(), name);
                }
                root.folderKvs.put(child.id, child);
                parent.children.put(name, child);
            }
            parent = child;
            i++;
        }
    }
}
