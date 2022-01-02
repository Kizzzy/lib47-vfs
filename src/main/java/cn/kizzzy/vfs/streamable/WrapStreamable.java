package cn.kizzzy.vfs.streamable;

import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.io.SubStream;

import java.io.InputStream;

public class WrapStreamable implements IStreamable {
    
    private final long offset;
    
    private final long size;
    
    private IStreamable source;
    
    public WrapStreamable(IStreamable source, long offset, long size) {
        this.source = source;
        this.offset = offset;
        this.size = size;
    }
    
    @Override
    public IStreamable getSource() {
        return source;
    }
    
    @Override
    public void setSource(IStreamable source) {
        this.source = source;
    }
    
    @Override
    public InputStream OpenStream() throws Exception {
        return new SubStream(source.OpenStream(), offset, size);
    }
}
