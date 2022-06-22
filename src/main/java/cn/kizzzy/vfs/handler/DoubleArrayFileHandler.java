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
        reader.setLittleEndian(littleEndian);
        return reader.readDoubles((int) (size / 8));
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, double[] data) throws Exception {
        writer.setLittleEndian(littleEndian);
        writer.writeDoubles(data);
        return true;
    }
}
