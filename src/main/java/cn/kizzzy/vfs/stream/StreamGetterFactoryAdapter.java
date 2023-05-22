package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.IStreamGetterFactory;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.tree.Leaf;

public abstract class StreamGetterFactoryAdapter implements IStreamGetterFactory {
    
    private final ITree tree;
    
    public StreamGetterFactoryAdapter(ITree tree) {
        this.tree = tree;
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(int id) {
        return getInputStreamGetter(tree.getLeaf(id));
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(String path) {
        return getInputStreamGetter(tree.getLeaf(path));
    }
    
    private IInputStreamGetter getInputStreamGetter(Leaf leaf) {
        if (leaf == null) {
            return null;
        }
        return getInputStreamGetterImpl(leaf);
    }
    
    protected abstract IInputStreamGetter getInputStreamGetterImpl(Leaf leaf);
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        return null;
    }
}
