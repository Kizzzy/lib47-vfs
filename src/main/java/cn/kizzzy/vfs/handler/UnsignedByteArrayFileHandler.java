package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class UnsignedByteArrayFileHandler implements IFileHandler<short[]> {
    
    @Override
    public short[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        return reader.readUnsignedBytes((int) size);
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, short[] data) throws Exception {
        writer.writeUnsignedBytes(data);
        return true;
    }
}
