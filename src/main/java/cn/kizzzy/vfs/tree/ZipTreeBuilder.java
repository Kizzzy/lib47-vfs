package cn.kizzzy.vfs.tree;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTreeBuilder extends TreeBuilder {
    
    protected final String zipFile;
    
    public ZipTreeBuilder(String zipFile) {
        this(zipFile, new IdGenerator());
    }
    
    public ZipTreeBuilder(String zipFile, IdGenerator idGenerator) {
        super(new Separator(Separator.SLASH, false), idGenerator);
        this.zipFile = zipFile;
    }
    
    @Override
    public ITree build() {
        try {
            try (ZipFile zipFile = new ZipFile(this.zipFile)) {
                Root root = new Root(idGenerator.getId(), zipFile.getName());
                
                for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements(); ) {
                    listImpl(root, root, e.nextElement());
                }
                return new Tree(root, separator);
            }
        } catch (Exception e) {
            LogHelper.error(String.format("build zip tree failed: %s", zipFile), e);
            return null;
        }
    }
    
    private void listImpl(Root root, Node parent, ZipEntry entry) {
        String[] names = separator.split(entry.getName());
        for (String name : names) {
            Node child = parent.children.get(name);
            if (child == null) {
                if (name.contains(".")) {
                    Leaf leaf = new Leaf(idGenerator.getId(), name, root.name, entry.getName(), null);
                    root.fileKvs.put(leaf.path, leaf);
                    child = leaf;
                } else {
                    child = new Node(idGenerator.getId(), name);
                }
                root.folderKvs.put(child.id, child);
                parent.children.put(name, child);
            }
            parent = child;
        }
    }
}
