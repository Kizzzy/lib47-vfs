package cn.kizzzy.vfs;

import cn.kizzzy.format.Ignore;
import cn.kizzzy.io.IFullyReader;

@Ignore
public interface IInputStreamGetter {
    
    IFullyReader getInput() throws Exception;
}
