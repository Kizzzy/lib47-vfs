package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

public class DoubleArrayFileHandler implements IFileHandler<double[]> {
    
    private final boolean littleEndian;
    
    public DoubleArrayFileHandler() {
        this(false);
    }
    
    public DoubleArrayFileHandler(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public double[] load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        if (littleEndian) {
            return reader.readDoubleExs((int) (size / 4));
        } else {
            return reader.readDoubles((int) (size / 4));
        }
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, double[] data) throws Exception {
        if (littleEndian) {
            writer.writeDoubleExs(data);
        } else {
            writer.writeDoubles(data);
        }
        return true;
    }
}
