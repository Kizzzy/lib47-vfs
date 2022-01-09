package cn.kizzzy.vfs.handler;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.io.FullyReader;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class TextFileHandler<T> implements IFileHandler<T> {
    
    protected final Charset charset;
    
    public TextFileHandler() {
        this(StandardCharsets.UTF_8);
    }
    
    public TextFileHandler(Charset charset) {
        this.charset = charset;
    }
    
    @Override
    public T load(IPackage pack, String path, FullyReader stream, long size) throws Exception {
        byte[] buffer = new byte[(int) size];
        stream.read(buffer);
        return loadImpl(new String(buffer, charset));
    }
    
    protected abstract T loadImpl(String str);
    
    @Override
    public boolean save(IPackage pack, String path, OutputStream stream, T data) throws Exception {
        byte[] bytes = saveImpl(data).getBytes(charset);
        stream.write(bytes);
        return true;
    }
    
    protected abstract String saveImpl(T data);
}
