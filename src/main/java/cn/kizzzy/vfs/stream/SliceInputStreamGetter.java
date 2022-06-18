package cn.kizzzy.vfs.stream;

import cn.kizzzy.helper.ZlibHelper;
import cn.kizzzy.io.ByteArrayInputStreamReader;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;
import cn.kizzzy.vfs.IInputStreamGetter;

public class SliceInputStreamGetter extends HolderInputStreamGetter {
    
    private final long offset;
    
    private final long size;
    
    private final boolean uncompress;
    
    public SliceInputStreamGetter(IInputStreamGetter source, long offset, long size) {
        this(source, offset, size, false);
    }
    
    public SliceInputStreamGetter(IInputStreamGetter source, long offset, long size, boolean uncompress) {
        setSource(source);
        this.offset = offset;
        this.size = size;
        this.uncompress = uncompress;
    }
    
    @Override
    public IFullyReader getInput() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        
        IFullyReader reader = new SliceFullReader(getSource().getInput(), offset, size);
        if (!uncompress) {
            return reader;
        }
        
        byte[] buffer = reader.readBytes((int) reader.length());
        buffer = ZlibHelper.uncompress(buffer);
        return new ByteArrayInputStreamReader(buffer);
    }
}
