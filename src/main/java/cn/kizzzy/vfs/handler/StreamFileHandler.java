package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.DataOutputStreamEx;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.io.FullyReader;

import java.io.OutputStream;

public abstract class StreamFileHandler<T> implements IFileHandler<T> {
    
    @Override
    public T load(IPackage pack, String path, FullyReader reader, long size) throws Exception {
        return loadImpl(pack, path, reader);
    }
    
    protected abstract T loadImpl(IPackage pack, String path, FullyReader reader) throws Exception;
    
    @Override
    public boolean save(IPackage pack, String path, OutputStream stream, T data) throws Exception {
        try (DataOutputStreamEx writer = new DataOutputStreamEx(stream)) {
            saveImpl(writer, data);
        }
        return true;
    }
    
    protected abstract void saveImpl(DataOutputStreamEx writer, T data) throws Exception;
}
