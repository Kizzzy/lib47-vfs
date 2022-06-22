package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class IntArrayHandler implements IFileHandler<int[]> {
    
    private final boolean littleEndian;
    
    public IntArrayHandler() {
        this(false);
    }
    
    public IntArrayHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public int[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        reader.setLittleEndian(littleEndian);
        return reader.readInts((int) (size / 4));
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, int[] data) throws Exception {
        writer.setLittleEndian(littleEndian);
        writer.writeInts(data);
        return true;
    }
}
