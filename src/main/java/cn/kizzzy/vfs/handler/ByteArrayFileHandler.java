package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class ByteArrayFileHandler implements IFileHandler<byte[]> {
    
    @Override
    public byte[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        return reader.readBytes((int) size);
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, byte[] data) throws Exception {
        writer.writeBytes(data);
        return true;
    }
}
