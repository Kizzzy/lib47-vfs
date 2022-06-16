package cn.kizzzy.vfs;

public interface IStreamGetterFactory {
    
    IInputStreamGetter getInputStreamGetter(String path);
    
    IOutputStreamGetter getOutputStreamGetter(String path);
}
