package cn.kizzzy.vfs.pack;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;
import cn.kizzzy.vfs.handler.BytesFileHandler;
import cn.kizzzy.vfs.handler.StringFileHandler;
import cn.kizzzy.vfs.tree.Leaf;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Page;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AbstractPackage implements IPackage {
    
    protected static final Separator FILE_SEPARATOR
        = new Separator(File.separatorChar, false);
    
    protected final String root;
    
    protected final ITree tree;
    
    protected final Map<Type, IFileHandler<?>> handlerKvs
        = new HashMap<>();
    
    public AbstractPackage(String root, ITree tree) {
        this.root = FILE_SEPARATOR.replace(root);
        this.tree = tree;
        
        initDefaultHandler();
    }
    
    protected void initDefaultHandler() {
        handlerKvs.put(byte[].class, new BytesFileHandler());
        handlerKvs.put(String.class, new StringFileHandler());
    }
    
    @Override
    public Map<Type, IFileHandler<?>> getHandlerKvs() {
        return handlerKvs;
    }
    
    @Override
    public IFileHandler<?> getHandler(Type clazz) {
        return handlerKvs.get(clazz);
    }
    
    @Override
    public Object load(String path, Type clazz) {
        IFileHandler<?> loader = getHandler(clazz);
        if (loader != null) {
            return load(path, loader);
        }
        return null;
    }
    
    @Override
    public Object load(String path, IFileLoader<?> loader) {
        try {
            if (exist(path)) {
                return loadImpl(path, loader);
            }
        } catch (Exception e) {
            LogHelper.error(String.format("load error: %s", path), e);
        }
        return null;
    }
    
    protected abstract Object loadImpl(String path, IFileLoader<?> loader) throws Exception;
    
    @Override
    public <T> boolean save(String path, T data) {
        IFileHandler<T> saver = (IFileHandler<T>) getHandler(data.getClass());
        if (saver != null) {
            return save(path, data, saver);
        }
        return false;
    }
    
    @Override
    public <T> boolean save(String path, T data, IFileSaver<T> saver) {
        try {
            return saveImpl(path, data, saver);
        } catch (Exception e) {
            LogHelper.error(String.format("save error: %s", path), e);
        }
        return false;
    }
    
    protected abstract <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception;
    
    @Override
    public boolean load() {
        return tree.load();
    }
    
    @Override
    public boolean stop() {
        return tree.stop();
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
