package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;
import cn.kizzzy.vfs.IStreamGetterFactory;

public class ZipStreamGetterFactory implements IStreamGetterFactory {
    
    private final String file;
    
    public ZipStreamGetterFactory(String file) {
        this.file = file;
    }
    
    @Override
    public IInputStreamGetter getInputStreamGetter(String path) {
        return new ZipStreamGetter(file, path);
    }
    
    @Override
    public IOutputStreamGetter getOutputStreamGetter(String path) {
        return new ZipStreamGetter(file, path);
    }
}
