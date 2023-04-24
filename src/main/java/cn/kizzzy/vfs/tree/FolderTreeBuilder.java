package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.ITreeBuilder;

import java.util.LinkedList;
import java.util.List;

public abstract class FolderTreeBuilder<IdxFile> implements ITreeBuilder {
    
    private final String folder;
    
    private final IPackage rootVfs;
    
    private final Class<IdxFile> clazz;
    
    private final IdGenerator idGenerator;
    
    public FolderTreeBuilder(IPackage rootVfs, String folder, Class<IdxFile> clazz, IdGenerator idGenerator) {
        this.rootVfs = rootVfs;
        this.folder = folder;
        this.clazz = clazz;
        this.idGenerator = idGenerator;
    }
    
    @Override
    public ITree build() {
        List<ITree> trees = new LinkedList<>();
        for (Node node : rootVfs.listNode(folder)) {
            if (node.leaf) {
                Leaf leaf = (Leaf) node;
                if (acceptLeaf(leaf)) {
                    IdxFile idxFile = rootVfs.load(leaf.path, clazz);
                    if (idxFile != null) {
                        ITree tree = buildTree(idxFile, idGenerator);
                        trees.add(tree);
                    }
                }
            }
        }
        
        return new Forest(trees);
    }
    
    protected abstract boolean acceptLeaf(Leaf leaf);
    
    protected abstract ITree buildTree(IdxFile idxFile, IdGenerator idGenerator);
}
