package cn.kizzzy.vfs.stream;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;
import cn.kizzzy.vfs.IInputStreamGetter;

public class SliceInputStreamGetter extends HolderInputStreamGetter {
    
    private final long offset;
    
    private final long size;
    
    public SliceInputStreamGetter(IInputStreamGetter source, long offset, long size) {
        setSource(source);
        this.offset = offset;
        this.size = size;
    }
    
    @Override
    public IFullyReader getInput() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        
        return new SliceFullReader(getSource().getInput(), offset, size);
    }
}
