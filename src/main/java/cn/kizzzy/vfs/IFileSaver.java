package cn.kizzzy.vfs;

import cn.kizzzy.io.FullyWriter;

public interface IFileSaver<T> {
    
    boolean save(IPackage vfs, String path, FullyWriter writer, T data) throws Exception;
}
