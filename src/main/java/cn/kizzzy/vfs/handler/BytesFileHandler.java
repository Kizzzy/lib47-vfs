package cn.kizzzy.vfs.handler;

public class BytesFileHandler extends BinaryFileHandler<byte[]> {
    
    @Override
    protected byte[] loadImpl(byte[] buffer) throws Exception {
        return buffer;
    }
    
    @Override
    protected byte[] saveImpl(byte[] data) throws Exception {
        return data;
    }
}
