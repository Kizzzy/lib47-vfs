package cn.kizzzy.vfs.pack;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IHolderInputStreamGetter;
import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.IStreamGetterFactory;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.handler.BytesFileHandler;
import cn.kizzzy.vfs.handler.LinesFileHandler;
import cn.kizzzy.vfs.handler.StringFileHandler;
import cn.kizzzy.vfs.provider.FileHandlerProvider;
import cn.kizzzy.vfs.tree.Leaf;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractPackage extends FileHandlerProvider implements IPackage {
    
    protected static final Logger logger = LoggerFactory.getLogger(IPackage.class);
    
    protected final ITree tree;
    
    protected final IStreamGetterFactory factory;
    
    public AbstractPackage(ITree tree, IStreamGetterFactory factory) {
        this.tree = tree;
        this.factory = factory;
        
        initDefaultHandler();
    }
    
    protected void initDefaultHandler() {
        addHandler(byte[].class, new BytesFileHandler());
        addHandler(String.class, new StringFileHandler());
        addHandler(String[].class, new LinesFileHandler());
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(int id) {
        return factory.getInputStreamGetter(id);
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(String path) {
        if (exist(path)) {
            return factory.getInputStreamGetter(path);
        }
        return null;
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        return factory.getOutputStreamGetter(path);
    }
    
    @Override
    public Object load(String path, IFileLoader<?> loader) {
        try {
            IInputStreamGetter getter = getInputStreamGetter(path);
            if (getter != null) {
                try (IFullyReader reader = getter.getInput()) {
                    Object obj = loader.load(this, path, reader, reader.length());
                    if (obj instanceof IHolderInputStreamGetter) {
                        ((IHolderInputStreamGetter) obj).setSource(getter);
                    }
                    return obj;
                }
            }
        } catch (Exception e) {
            logger.error("load error: {}", path, e);
        }
        return null;
    }
    
    @Override
    public <T> boolean save(String path, T data, IFileSaver<T> saver) {
        try {
            IOutputStreamGetter getter = getOutputStreamGetter(path);
            if (getter != null) {
                try (IFullyWriter writer = getter.getOutput()) {
                    return saver.save(this, path, writer, data);
                }
            }
        } catch (Exception e) {
            logger.error("save error: %{}", path, e);
        }
        return false;
    }
    
    @Override
    public boolean load() {
        return tree.load();
    }
    
    @Override
    public boolean stop() {
        return tree.stop();
    }
    
    @Override
    public Leaf getLeaf(int id) {
        return tree.getLeaf(id);
    }
    
    @Override
    public Leaf getLeaf(String path) {
        return tree.getLeaf(path);
    }
    
    @Override
    public Node getNode(int id) {
        return tree.getNode(id);
    }
    
    @Override
    public Page getPage(String path, int index, int size) {
        return tree.getPage(path, index, size);
    }
    
    @Override
    public List<Leaf> listLeaf(int id) {
        return tree.listLeaf(id);
    }
    
    @Override
    public List<Leaf> listLeaf(int id, boolean recursively) {
        return tree.listLeaf(id, recursively);
    }
    
    @Override
    public List<Leaf> listLeaf(Node node) {
        return tree.listLeaf(node);
    }
    
    @Override
    public List<Leaf> listLeaf(Node node, boolean recursively) {
        return tree.listLeaf(node, recursively);
    }
    
    @Override
    public List<Node> listNode(int id) {
        return tree.listNode(id);
    }
    
    @Override
    public List<Node> listNode(int id, boolean recursively) {
        return tree.listNode(id, recursively);
    }
    
    @Override
    public List<Node> listNode(String path) {
        return tree.listNode(path);
    }
    
    @Override
    public List<Node> listNode(String path, boolean recursively) {
        return tree.listNode(path, recursively);
    }
    
    @Override
    public List<Node> listNodeByRegex(String pattern) {
        return tree.listNodeByRegex(pattern);
    }
}
