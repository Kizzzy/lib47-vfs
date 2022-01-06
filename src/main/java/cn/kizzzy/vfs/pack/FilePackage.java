package cn.kizzzy.vfs.pack;

import cn.kizzzy.helper.FileHelper;
import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStrategy;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.streamable.FileStreamable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePackage extends PackageAdapter {
    
    public FilePackage(String root) {
        this(root, null);
    }
    
    public FilePackage(String root, IStrategy<String, Object> strategy) {
        super(root, '\\', strategy);
    }
    
    @Override
    public boolean exist(String path) {
        Path fullPath = Paths.get(root, path);
        return fullPath.toFile().exists();
    }
    
    @Override
    protected Object loadImpl(String path, IFileLoader<?> loader) throws Exception {
        Path fullPath = Paths.get(root, path);
        try (FileInputStream fis = new FileInputStream(fullPath.toFile())) {
            Object obj = loader.load(this, path, fis, fis.available());
            if (obj instanceof IStreamable) {
                ((IStreamable) obj).setSource(new FileStreamable(fullPath.toString()));
            }
            return obj;
        }
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        Path fullPath = Paths.get(root, path);
        if (FileHelper.createFolderIfAbsent(fullPath.getParent().toFile())) {
            try (FileOutputStream fos = new FileOutputStream(fullPath.toFile())) {
                return saver.save(this, path, fos, data);
            }
        } else {
            LogHelper.error("Save Data Failed: Create Folder Failed");
            return false;
        }
    }
}
