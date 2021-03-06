package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IHolderInputStreamGetter;
import cn.kizzzy.vfs.IStreamGetterFactory;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.stream.LeafStreamGetterFactory;

import java.util.function.Function;

public class LeafPackage<T extends IHolderInputStreamGetter> extends AbstractPackage {
    
    public LeafPackage(ITree tree, IStreamGetterFactory factory, Class<T> clazz, Function<T, String> callback) {
        super(tree, new LeafStreamGetterFactory<>(tree, factory, clazz, callback));
    }
}
