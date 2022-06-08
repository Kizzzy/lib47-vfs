package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class FloatArrayFileHandler implements IFileHandler<float[]> {
    
    private final boolean littleEndian;
    
    public FloatArrayFileHandler() {
        this(false);
    }
    
    public FloatArrayFileHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public float[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        if (littleEndian) {
            return reader.readFloatExs((int) (size / 4));
        } else {
            return reader.readFloats((int) (size / 4));
        }
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, float[] data) throws Exception {
        if (littleEndian) {
            writer.writeFloatExs(data);
        } else {
            writer.writeFloats(data);
        }
        return true;
    }
}
