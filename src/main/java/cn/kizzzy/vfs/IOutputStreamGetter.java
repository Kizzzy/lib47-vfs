package cn.kizzzy.vfs;

import cn.kizzzy.io.IFullyWriter;

public interface IOutputStreamGetter {
    
    IFullyWriter getOutput() throws Exception;
}
