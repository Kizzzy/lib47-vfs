package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.IStreamGetterFactory;
import cn.kizzzy.vfs.Separator;

public class FileStreamGetterFactory implements IStreamGetterFactory {
    
    private final String root;
    
    public FileStreamGetterFactory(String root) {
        this.root = Separator.FILE_SEPARATOR.replace(root);
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(String path) {
        String fullPath = Separator.FILE_SEPARATOR.combine(root, path);
        return new FileStreamGetter(fullPath);
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        String fullPath = Separator.FILE_SEPARATOR.combine(root, path);
        return new FileStreamGetter(fullPath);
    }
}
