package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.streamable.FileStreamGetterFactory;
import cn.kizzzy.vfs.streamable.LeafStreamGetterFactory;

import java.util.function.Function;

public class LeafPackage<T extends IInputStreamGetter> extends AbstractPackage {
    
    public LeafPackage(String root, ITree tree, Class<T> clazz, Function<T, String> callback) {
        super(tree, new LeafStreamGetterFactory<>(tree, new FileStreamGetterFactory(root), clazz, callback));
    }
}
