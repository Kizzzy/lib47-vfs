package cn.kizzzy.vfs.handler;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedImageHandler implements IFileHandler<BufferedImage> {
    
    private final String format;
    
    public BufferedImageHandler() {
        this("png");
    }
    
    public BufferedImageHandler(String format) {
        this.format = format;
    }
    
    @Override
    public BufferedImage load(IPackage iPackage, String s, InputStream inputStream, long l) throws Exception {
        return ImageIO.read(inputStream);
    }
    
    @Override
    public boolean save(IPackage iPackage, String s, OutputStream outputStream, BufferedImage bufferedImage) throws Exception {
        ImageIO.write(bufferedImage, format, outputStream);
        return true;
    }
}
