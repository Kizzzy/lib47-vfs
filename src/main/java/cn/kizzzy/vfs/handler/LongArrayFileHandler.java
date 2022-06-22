package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class LongArrayFileHandler implements IFileHandler<long[]> {
    
    private final boolean littleEndian;
    
    public LongArrayFileHandler() {
        this(false);
    }
    
    public LongArrayFileHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public long[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        reader.setLittleEndian(littleEndian);
        return reader.readLongs((int) (size / 8));
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, long[] data) throws Exception {
        writer.setLittleEndian(littleEndian);
        writer.writeLongs(data);
        return true;
    }
}
