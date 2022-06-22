package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class UnsignedShortArrayFileHandler implements IFileHandler<int[]> {
    
    private final boolean littleEndian;
    
    public UnsignedShortArrayFileHandler() {
        this(false);
    }
    
    public UnsignedShortArrayFileHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public int[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        reader.setLittleEndian(littleEndian);
        return reader.readUnsignedShorts((int) (size / 2));
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, int[] data) throws Exception {
        writer.setLittleEndian(littleEndian);
        writer.writeUnsignedShorts(data);
        return true;
    }
}
