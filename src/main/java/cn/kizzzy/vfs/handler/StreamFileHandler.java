package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.DataOutputStreamEx;
import cn.kizzzy.io.SubStream;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class StreamFileHandler<T> implements IFileHandler<T> {
    
    @Override
    public T load(IPackage pack, String path, InputStream stream, long size) throws Exception {
        try (SubStream subStream = new SubStream(stream, 0, size)) {
            return loadImpl(pack, path, subStream);
        }
    }
    
    protected abstract T loadImpl(IPackage pack, String path, SubStream reader) throws Exception;
    
    @Override
    public boolean save(IPackage pack, String path, OutputStream stream, T data) throws Exception {
        try (DataOutputStreamEx writer = new DataOutputStreamEx(stream)) {
            saveImpl(writer, data);
        }
        return true;
    }
    
    protected abstract void saveImpl(DataOutputStreamEx writer, T data) throws Exception;
}
