package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.streamable.FileStreamGetterFactory;
import cn.kizzzy.vfs.tree.EmptyTree;

public class FilePackage extends AbstractPackage {
    
    public FilePackage(String root) {
        this(root, new EmptyTree());
    }
    
    public FilePackage(String root, ITree tree) {
        super(tree, new FileStreamGetterFactory(root));
    }
}
