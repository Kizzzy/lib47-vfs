package cn.kizzzy.vfs.tree;

import java.io.File;

public class FileTreeBuilder<T> {
    
    public Root<T> build(File file) {
        String rootPath = file.getAbsolutePath().toLowerCase();
        Root<T> pack = new Root<>(file.getName());
        listImpl(pack, pack, file, rootPath, true);
        return pack;
    }
    
    protected void listImpl(Root<T> pack, Node<T> parent, File file, String rootPath, boolean isRoot) {
        if (file.isDirectory()) {
            if (!isRoot) {
                Node<T> temp = new Node<>(file.getName());
                parent.children.put(temp.name, temp);
                parent = temp;
            }
            
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    listImpl(pack, parent, child, rootPath, false);
                }
            }
            
            pack.folderKvs.put(parent.id, parent);
        } else {
            Node<T> temp = new Node<>(file.getName());
            parent.children.put(temp.name, temp);
            
            String path = file.getAbsolutePath().toLowerCase().replace(rootPath + "\\", "");
            
            Leaf<T> leaf = new Leaf<>();
            leaf.pack = pack.path;
            leaf.path = path;
            leaf.item = create(file);
            
            temp.thumbs = leaf;
            
            pack.fileKvs.put(leaf.path, leaf);
        }
    }
    
    protected T create(File file) {
        return null;
    }
}
