package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.FullyReader;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import java.io.OutputStream;

public abstract class BinaryFileHandler<T> implements IFileHandler<T> {
    
    @Override
    public T load(IPackage pack, String path, FullyReader stream, long size) throws Exception {
        byte[] buffer = new byte[(int) size];
        stream.read(buffer);
        return fromBytes(buffer);
    }
    
    protected abstract T fromBytes(byte[] buffer) throws Exception;
    
    @Override
    public boolean save(IPackage pack, String path, OutputStream stream, T data) throws Exception {
        stream.write(toBytes(data));
        return true;
    }
    
    protected abstract byte[] toBytes(T data) throws Exception;
}
