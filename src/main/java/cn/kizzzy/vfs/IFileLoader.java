package cn.kizzzy.vfs;

import java.io.InputStream;

public interface IFileLoader<T> {
    
    T load(IPackage pack, String path, InputStream stream, long size) throws Exception;
}
