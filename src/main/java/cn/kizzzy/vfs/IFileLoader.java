package cn.kizzzy.vfs;

import cn.kizzzy.io.FullyReader;

public interface IFileLoader<T> {
    
    T load(IPackage vfs, String path, FullyReader stream, long size) throws Exception;
}
