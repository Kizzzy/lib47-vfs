package cn.kizzzy.vfs.handler;

public class BytesFileHandler extends BinaryFileHandler<byte[]> {
    
    @Override
    protected byte[] fromBytes(byte[] buffer) throws Exception {
        return buffer;
    }
    
    @Override
    protected byte[] toBytes(byte[] data) throws Exception {
        return data;
    }
}
