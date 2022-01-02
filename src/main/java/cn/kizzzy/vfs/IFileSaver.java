package cn.kizzzy.vfs;

import java.io.OutputStream;

public interface IFileSaver<T> {
    
    boolean save(IPackage pack, String path, OutputStream stream, T data) throws Exception;
}
