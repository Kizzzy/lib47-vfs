package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.ITreeBuilder;
import cn.kizzzy.vfs.Separator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TreeBuilder implements ITreeBuilder {
    
    protected static final Logger logger = LoggerFactory.getLogger(TreeBuilder.class);
    
    protected final Separator separator;
    
    protected final IdGenerator idGenerator;
    
    public TreeBuilder(Separator separator, IdGenerator idGenerator) {
        this.separator = separator;
        this.idGenerator = idGenerator;
    }
}
