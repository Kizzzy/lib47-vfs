package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStrategy;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipPackage extends PackageAdapter {
    
    public ZipPackage(String root) {
        this(root, null);
    }
    
    public ZipPackage(String root, IStrategy<String, Object> strategy) {
        super(root, '/', strategy);
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
    protected Object loadImpl(String path, IFileLoader<?> loader) throws Exception {
        try (ZipFile zipFile = new ZipFile(root)) {
            ZipEntry entry = zipFile.getEntry(path);
            if (entry != null) {
                return loader.load(this, path, zipFile.getInputStream(entry), entry.getSize());
            }
        }
        return null;
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        /*try (ZipFile zipFile = new ZipFile(root)) {
            ZipEntry entry = zipFile.getEntry(path);
            return saver.save(zipFile.getOutputStream(entry), data);
        }*/
        return false;
    }
}
