package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.FullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public abstract class BinaryFileHandler<T> implements IFileHandler<T> {
    
    @Override
    public T load(IPackage vfs, String path, FullyReader stream, long size) throws Exception {
        byte[] buffer = new byte[(int) size];
        stream.read(buffer);
        return fromBytes(buffer);
    }
    
    protected abstract T fromBytes(byte[] buffer) throws Exception;
    
    @Override
    public boolean save(IPackage vfs, String path, FullyWriter writer, T data) throws Exception {
        writer.write(toBytes(data));
        return true;
    }
    
    protected abstract byte[] toBytes(T data) throws Exception;
}
