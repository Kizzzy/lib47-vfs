package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.tree.Leaf;

public class ZipStreamGetterFactory extends StreamGetterFactoryAdapter {
    
    private final String file;
    
    public ZipStreamGetterFactory(String file, ITree tree) {
        super(tree);
        this.file = file;
    }
    
    @Override
    protected IInputStreamGetter getInputStreamGetterImpl(Leaf leaf) {
        return new ZipStreamGetter(file, leaf.path);
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        return new ZipStreamGetter(file, path);
    }
}
