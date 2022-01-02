package cn.kizzzy.vfs.handler;

import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesHandler implements IFileHandler<Properties> {
    
    @Override
    public Properties load(IPackage pack, String path, InputStream stream, long size) throws Exception {
        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }
    
    @Override
    public boolean save(IPackage pack, String path, OutputStream stream, Properties data) throws Exception {
        data.store(stream, null);
        return true;
    }
}
