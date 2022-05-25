package cn.kizzzy.vfs;

import cn.kizzzy.io.IFullyReader;

public interface IFileLoader<T> {
    
    T load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception;
}
