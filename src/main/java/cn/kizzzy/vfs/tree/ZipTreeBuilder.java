package cn.kizzzy.vfs.tree;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTreeBuilder<T> extends TreeBuilder<T> {
    
    protected final String zipFile;
    
    public ZipTreeBuilder(String zipFile, IdGenerator idGenerator) {
        super(Separator.SLASH_SEPARATOR_LOWERCASE, idGenerator);
        this.zipFile = zipFile;
    }
    
    @Override
    public ITree<T> build() {
        try {
            try (ZipFile zipFile = new ZipFile(this.zipFile)) {
                Root<T> root = new Root<>(idGenerator.getId(), zipFile.getName());
                
                for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements(); ) {
                    listImpl(root, root, e.nextElement());
                }
                return new Tree<>(root, separator);
            }
        } catch (Exception e) {
            LogHelper.error("build zip tree failed", e);
            return null;
        }
    }
    
    private void listImpl(Root<T> root, Node<T> parent, ZipEntry entry) {
        String[] names = separator.split(entry.getName());
        for (String name : names) {
            Node<T> child = parent.children.get(name);
            if (child == null) {
                if (name.contains(".")) {
                    Leaf<T> leaf = new Leaf<>(idGenerator.getId(), name, root.name, entry.getName(), create(entry));
                    root.fileKvs.put(leaf.path, leaf);
                    child = leaf;
                } else {
                    child = new Node<>(idGenerator.getId(), name);
                    root.folderKvs.put(child.id, child);
                }
                parent.children.put(name, child);
            }
            parent = child;
        }
    }
    
    protected T create(ZipEntry file) {
        return null;
    }
}
