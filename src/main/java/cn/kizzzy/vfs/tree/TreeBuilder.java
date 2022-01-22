package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

public abstract class TreeBuilder {
    
    protected final Separator separator;
    
    protected final IdGenerator idGenerator;
    
    public TreeBuilder(Separator separator, IdGenerator idGenerator) {
        this.separator = separator;
        this.idGenerator = idGenerator;
    }
    
    public abstract ITree build();
}
