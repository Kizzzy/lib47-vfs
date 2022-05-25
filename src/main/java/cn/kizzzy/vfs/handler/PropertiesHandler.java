package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesHandler implements IFileHandler<Properties> {
    
    @Override
    public Properties load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        Properties properties = new Properties();
        properties.load((InputStream) reader);
        return properties;
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, Properties data) throws Exception {
        data.store((OutputStream) writer, null);
        return true;
    }
}
