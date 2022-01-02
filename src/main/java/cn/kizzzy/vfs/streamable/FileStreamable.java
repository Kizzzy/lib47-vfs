package cn.kizzzy.vfs.streamable;

import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.io.SubStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileStreamable implements IStreamable {
    
    private final String fullPath;
    
    private IStreamable source;
    
    public FileStreamable(String fullPath) {
        this.fullPath = fullPath;
    }
    
    @Override
    public IStreamable getSource() {
        return source;
    }
    
    @Override
    public void setSource(IStreamable source) {
        this.source = source;
    }
    
    public InputStream OpenStream() throws Exception {
        FileInputStream fis = new FileInputStream(fullPath);
        return new SubStream(fis, 0, fis.available());
    }
}
