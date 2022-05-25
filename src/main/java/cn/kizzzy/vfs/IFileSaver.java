package cn.kizzzy.vfs;

import cn.kizzzy.io.IFullyWriter;

public interface IFileSaver<T> {
    
    boolean save(IPackage vfs, String path, IFullyWriter writer, T data) throws Exception;
}
