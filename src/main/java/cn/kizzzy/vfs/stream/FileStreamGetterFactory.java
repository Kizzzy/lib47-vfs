package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;
import cn.kizzzy.vfs.tree.Leaf;

public class FileStreamGetterFactory extends StreamGetterFactoryAdapter {
    
    private final String root;
    
    public FileStreamGetterFactory(String root, ITree tree) {
        super(tree);
        this.root = Separator.FILE_SEPARATOR.replace(root);
    }
    
    @Override
    protected IInputStreamGetter getInputStreamGetterImpl(Leaf leaf) {
        String fullPath = Separator.FILE_SEPARATOR.combine(root, leaf.path);
        return new FileStreamGetter(fullPath);
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        String fullPath = Separator.FILE_SEPARATOR.combine(root, path);
        return new FileStreamGetter(fullPath);
    }
}
