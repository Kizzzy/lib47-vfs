package cn.kizzzy.vfs.tree;

import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.ITreeBuilder;

import java.util.LinkedList;
import java.util.List;

public abstract class FolderTreeBuilder implements ITreeBuilder {
    
    private final String root;
    
    private final String folder;
    
    private final IdGenerator idGenerator;
    
    public FolderTreeBuilder(String root, String folder, IdGenerator idGenerator) {
        this.root = root;
        this.folder = folder;
        this.idGenerator = idGenerator;
    }
    
    @Override
    public ITree build() {
        List<ITree> trees = new LinkedList<>();
        trees.add(new FileTreeBuilder(root, idGenerator).build());
        
        IPackage rootVfs = getRootVfs(root);
        for (Node node : rootVfs.listNode(folder)) {
            if (node.leaf) {
                Leaf leaf = (Leaf) node;
                ITree tree = buildTree(rootVfs, leaf, idGenerator);
                if (tree != null) {
                    trees.add(tree);
                }
            }
        }
        
        return new Forest(trees);
    }
    
    protected abstract IPackage getRootVfs(String root);
    
    protected abstract ITree buildTree(IPackage rootVfs, Leaf leaf, IdGenerator idGenerator);
}
