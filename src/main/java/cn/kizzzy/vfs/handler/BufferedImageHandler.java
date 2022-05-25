package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BufferedImageHandler implements IFileHandler<BufferedImage> {
    
    private final String format;
    
    public BufferedImageHandler() {
        this("png");
    }
    
    public BufferedImageHandler(String format) {
        this.format = format;
    }
    
    @Override
    public BufferedImage load(IPackage vfs, String s, IFullyReader reader, long size) throws Exception {
        return ImageIO.read(reader.asInputStream());
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, BufferedImage data) throws Exception {
        ImageIO.write(data, format, writer.asOutputStream());
        return true;
    }
}
