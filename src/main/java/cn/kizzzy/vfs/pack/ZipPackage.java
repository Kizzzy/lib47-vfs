package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStrategy;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.io.FullyReader;
import cn.kizzzy.vfs.streamable.ZipStreamable;

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
        ZipStreamable streamable = new ZipStreamable(root, path);
        try (FullyReader reader = streamable.OpenStream()) {
            Object obj = loader.load(this, path, reader, reader.length());
            if (obj instanceof IStreamable) {
                ((IStreamable) obj).setSource(streamable);
            }
            return obj;
        }
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
