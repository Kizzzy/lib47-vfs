package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

import java.io.File;

public class FileTreeBuilder<T> extends TreeBuilder<T> {
    
    protected final String rootPath;
    
    public FileTreeBuilder(String rootPath, IdGenerator idGenerator) {
        super(new Separator(File.separatorChar, false), idGenerator);
        this.rootPath = separator.replace(rootPath);
    }
    
    @Override
    public ITree<T> build() {
        File file = new File(rootPath);
        Root<T> root = new Root<>(idGenerator.getId(), separator.replace(file.getName()));
        listImpl(root, root, file, true);
        return new Tree<>(root, separator);
    }
    
    protected void listImpl(Root<T> root, Node<T> parent, File file, boolean isRoot) {
        if (file.isDirectory()) {
            if (!isRoot) {
                Node<T> temp = new Node<>(idGenerator.getId(), separator.replace(file.getName()));
                parent.children.put(temp.name, temp);
                parent = temp;
            }
            
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    listImpl(root, parent, child, false);
                }
            }
            
            root.folderKvs.put(parent.id, parent);
        } else {
            String path = separator.replace(file.getAbsolutePath());
            path = path.replace(String.format("%s%s", rootPath, separator.getDesired()), "");
            
            Leaf<T> leaf = new Leaf<>(idGenerator.getId(), separator.replace(file.getName()), root.name, path, create(file));
            parent.children.put(leaf.name, leaf);
            
            root.folderKvs.put(leaf.id, leaf);
            root.fileKvs.put(leaf.path, leaf);
        }
    }
    
    protected T create(File file) {
        return null;
    }
}
