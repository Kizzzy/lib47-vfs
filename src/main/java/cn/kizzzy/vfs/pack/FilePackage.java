package cn.kizzzy.vfs.pack;

import cn.kizzzy.helper.FileHelper;
import cn.kizzzy.io.FullyReader;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.streamable.FileStreamable;
import cn.kizzzy.vfs.tree.EmptyTree;

import java.io.File;
import java.io.FileOutputStream;

public class FilePackage extends AbstractPackage {
    
    public FilePackage(String root) {
        this(root, new EmptyTree());
    }
    
    public FilePackage(String root, ITree tree) {
        super(root, tree);
    }
    
    @Override
    public boolean exist(String path) {
        path = FILE_SEPARATOR.combine(root, path);
        return new File(path).exists();
    }
    
    @Override
    protected Object loadImpl(String path, IFileLoader<?> loader) throws Exception {
        String fullPath = FILE_SEPARATOR.combine(root, path);
        FileStreamable streamable = new FileStreamable(fullPath);
        try (FullyReader stream = streamable.OpenStream()) {
            Object obj = loader.load(this, path, stream, stream.length());
            if (obj instanceof IStreamable) {
                ((IStreamable) obj).setSource(streamable);
            }
            return obj;
        }
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        String fullPath = FILE_SEPARATOR.combine(root, path);
        if (!FileHelper.createFolderIfAbsent(new File(fullPath).getParent())) {
            throw new RuntimeException("create folder failed: " + fullPath);
        }
        
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            return saver.save(this, path, fos, data);
        }
    }
}
