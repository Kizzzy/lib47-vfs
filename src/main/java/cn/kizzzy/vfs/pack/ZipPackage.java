package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.stream.ZipStreamGetterFactory;
import cn.kizzzy.vfs.tree.ZipTreeBuilder;

public class ZipPackage extends AbstractPackage {
    
    public ZipPackage(String file) {
        this(file, new ZipTreeBuilder(file).build());
    }
    
    public ZipPackage(String file, ITree tree) {
        super(tree, new ZipStreamGetterFactory(file));
    }
}
