package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public abstract class BinaryFileHandler<T> implements IFileHandler<T> {
    
    @Override
    public T load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        byte[] buffer = new byte[(int) size];
        reader.read(buffer);
        return fromBytes(buffer);
    }
    
    protected abstract T fromBytes(byte[] buffer) throws Exception;
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, T data) throws Exception {
        writer.write(toBytes(data));
        return true;
    }
    
    protected abstract byte[] toBytes(T data) throws Exception;
}
