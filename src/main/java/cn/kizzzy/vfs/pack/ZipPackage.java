package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.streamable.ZipStreamable;
import cn.kizzzy.vfs.tree.EmptyTree;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipPackage extends AbstractPackage {
    
    public ZipPackage(String root) {
        this(root, new EmptyTree());
    }
    
    public ZipPackage(String root, ITree tree) {
        super(root, tree);
    }
    
    @Override
    public boolean exist(String path) {
        try (ZipFile zipFile = new ZipFile(root)) {
            ZipEntry entry = zipFile.getEntry(path);
            return entry != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    protected IStreamable getStreamableImpl(String path) {
        return new ZipStreamable(root, path);
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        return false;
    }
}
