package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.IStreamGetterFactory;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.tree.Leaf;

import java.util.function.Function;

@SuppressWarnings("unchecked")
public class LeafStreamGetterFactory<T extends IInputStreamGetter> implements IStreamGetterFactory {
    
    private final ITree tree;
    
    private final IStreamGetterFactory factory;
    
    private final Class<T> clazz;
    
    private final Function<T, String> callback;
    
    public LeafStreamGetterFactory(ITree tree, IStreamGetterFactory factory, Class<T> clazz, Function<T, String> callback) {
        this.tree = tree;
        this.factory = factory;
        this.clazz = clazz;
        this.callback = callback;
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(String path) {
        Leaf leaf = tree.getLeaf(path);
        if (!clazz.isInstance(leaf.item)) {
            return null;
        }
        
        T entry = (T) leaf.item;
        if (entry.getSource() == null) {
            IInputStreamGetter getter = factory.getInputStreamGetter(callback.apply(entry));
            if (getter == null) {
                return null;
            }
            entry.setSource(getter);
        }
        
        return entry;
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        return null;
    }
}
