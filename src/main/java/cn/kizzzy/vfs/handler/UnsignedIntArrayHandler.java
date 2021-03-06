package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class UnsignedIntArrayHandler implements IFileHandler<long[]> {
    
    private final boolean littleEndian;
    
    public UnsignedIntArrayHandler() {
        this(false);
    }
    
    public UnsignedIntArrayHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public long[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        reader.setLittleEndian(littleEndian);
        return reader.readUnsignedInts((int) (size / 4));
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, long[] data) throws Exception {
        writer.setLittleEndian(littleEndian);
        writer.writeUnsignedInts(data);
        return true;
    }
}
