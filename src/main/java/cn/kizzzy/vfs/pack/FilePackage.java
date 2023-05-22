package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;
import cn.kizzzy.vfs.stream.FileStreamGetterFactory;
import cn.kizzzy.vfs.tree.FileTreeBuilder;

import java.io.File;

public class FilePackage extends AbstractPackage {
    
    private final String root;
    
    public FilePackage(String root) {
        this(root, new FileTreeBuilder(root).build());
    }
    
    public FilePackage(String root, ITree tree) {
        super(tree, new FileStreamGetterFactory(root, tree));
        this.root = root;
    }
    
    @Override
    public boolean exist(String path) {
        path = Separator.FILE_SEPARATOR.combine(root, path);
        return new File(path).exists();
    }
}
