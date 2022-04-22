package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.FullyWriter;
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
    public BufferedImage load(IPackage vfs, String s, FullyReader inputStream, long l) throws Exception {
        return ImageIO.read(inputStream);
    }
    
    @Override
    public boolean save(IPackage vfs, String s, FullyWriter writer, BufferedImage bufferedImage) throws Exception {
        ImageIO.write(bufferedImage, format, writer);
        return true;
    }
}
