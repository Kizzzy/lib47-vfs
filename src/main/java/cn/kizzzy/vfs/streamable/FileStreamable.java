package cn.kizzzy.vfs.streamable;

import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.RandomAccessFileReader;

import java.io.RandomAccessFile;

public class FileStreamable implements IStreamable {
    
    private final String file;
    
    private IStreamable source;
    
    public FileStreamable(String file) {
        this.file = file;
    }
    
    @Override
    public IStreamable getSource() {
        return source;
    }
    
    @Override
    public void setSource(IStreamable source) {
        this.source = source;
    }
    
    public FullyReader OpenStream() throws Exception {
        return new RandomAccessFileReader(new RandomAccessFile(file, "r"));
    }
}
