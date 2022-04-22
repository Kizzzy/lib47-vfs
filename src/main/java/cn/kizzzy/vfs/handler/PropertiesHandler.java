package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.FullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import java.util.Properties;

public class PropertiesHandler implements IFileHandler<Properties> {
    
    @Override
    public Properties load(IPackage vfs, String path, FullyReader stream, long size) throws Exception {
        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }
    
    @Override
    public boolean save(IPackage vfs, String path, FullyWriter writer, Properties data) throws Exception {
        data.store(writer, null);
        return true;
    }
}
