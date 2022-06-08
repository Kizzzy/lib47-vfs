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
        if (littleEndian) {
            return reader.readIntExs((int) (size / 4));
        } else {
            return reader.readInts((int) (size / 4));
        }
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, int[] data) throws Exception {
        if (littleEndian) {
            writer.writeIntExs(data);
        } else {
            writer.writeInts(data);
        }
        return true;
    }
}
