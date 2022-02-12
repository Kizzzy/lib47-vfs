package cn.kizzzy.vfs.handler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class TextFileHandler<T> extends BinaryFileHandler<T> {
    
    protected final Charset charset;
    
    public TextFileHandler() {
        this(StandardCharsets.UTF_8);
    }
    
    public TextFileHandler(Charset charset) {
        this.charset = charset;
    }
    
    @Override
    protected T fromBytes(byte[] buffer) throws Exception {
        return loadImpl(new String(buffer, charset));
    }
    
    protected abstract T loadImpl(String str);
    
    @Override
    protected byte[] toBytes(T data) throws Exception {
        return saveImpl(data).getBytes(charset);
    }
    
    protected abstract String saveImpl(T data);
}
