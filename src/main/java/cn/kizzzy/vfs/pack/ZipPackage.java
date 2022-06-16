package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.streamable.ZipStreamGetterFactory;
import cn.kizzzy.vfs.tree.EmptyTree;

public class ZipPackage extends AbstractPackage {
    
    public ZipPackage(String root) {
        this(root, new EmptyTree());
    }
    
    public ZipPackage(String root, ITree tree) {
        super(tree, new ZipStreamGetterFactory(root));
    }
}
