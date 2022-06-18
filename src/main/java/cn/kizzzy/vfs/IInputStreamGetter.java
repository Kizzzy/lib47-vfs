package cn.kizzzy.vfs;

import cn.kizzzy.io.IFullyReader;

public interface IInputStreamGetter {
    
    IFullyReader getInput() throws Exception;
}
