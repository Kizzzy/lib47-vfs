package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class ShortArrayFileHandler implements IFileHandler<short[]> {
    
    private final boolean littleEndian;
    
    public ShortArrayFileHandler() {
        this(false);
    }
    
    public ShortArrayFileHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public short[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        reader.setLittleEndian(littleEndian);
        return reader.readShorts((int) (size / 2));
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, short[] data) throws Exception {
        writer.setLittleEndian(littleEndian);
        writer.writeShorts(data);
        return true;
    }
}
